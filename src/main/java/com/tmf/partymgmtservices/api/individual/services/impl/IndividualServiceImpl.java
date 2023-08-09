package com.tmf.partymgmtservices.api.individual.services.impl;

import com.tmf.partymgmtservices.api.exceptions.BadRequestException;
import com.tmf.partymgmtservices.api.exceptions.NotFoundException;
import com.tmf.partymgmtservices.api.individual.domain.model.*;
import com.tmf.partymgmtservices.api.individual.domain.request.*;
import com.tmf.partymgmtservices.api.individual.domain.response.IndividualResponse;
import com.tmf.partymgmtservices.api.individual.services.IndividualService;
import com.tmf.partymgmtservices.api.util.Defs;
import com.tmf.partymgmtservices.repository.db.model.IndividualDaoReadRepository;
import com.tmf.partymgmtservices.repository.db.model.IndividualDaoWriteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class IndividualServiceImpl implements IndividualService {
    private static final Logger LOGGER = LogManager.getLogger(IndividualServiceImpl.class);

    private final IndividualDaoWriteRepository individualDaoWriteRepository;
    private final IndividualDaoReadRepository individualDaoReadRepository;

    @Autowired
    IndividualServiceImpl(IndividualDaoWriteRepository individualDaoWriteRepository, IndividualDaoReadRepository individualDaoReadRepository){
        this.individualDaoWriteRepository = individualDaoWriteRepository;
        this.individualDaoReadRepository = individualDaoReadRepository;
    }

    @Override
    public IndividualResponse saveIndividual(IndividualRequest individualRequest) throws Exception {
        if(!StringUtils.hasLength(individualRequest.getFamilyName())){
            throw new BadRequestException("Family Name must be provided!");
        }
        if(!StringUtils.hasLength(individualRequest.getGivenName())){
            throw new BadRequestException("Given Name must be provided!");
        }

        Individual individual = new Individual();
        String individualId = individualDaoWriteRepository.getNextSequenceValue(Defs.INDIVIDUAL_ID_SEQUENCE).toString();
        individualRequest.setId(individualId);
        BeanUtils.copyProperties(individualRequest, individual);
        individualDaoWriteRepository.saveIndividual(individual);

        if(individualRequest.getContactMedium()!=null && individualRequest.getContactMedium().size()>0){
            LOGGER.info("Saving contact medium...");
            saveContactMedium(individualRequest.getContactMedium(), individualId, null);
        }
        if(individualRequest.getCreditRating()!=null && individualRequest.getCreditRating().size()>0){
            LOGGER.info("Saving credit rating...");
            saveCreditRatings(individualRequest.getCreditRating(), individualId, null);
        }
        if(individualRequest.getDisability()!=null && individualRequest.getDisability().size()>0){
            LOGGER.info("Saving disabilities...");
            saveDisabilities(individualRequest.getDisability(), individualId);
        }
        if(individualRequest.getExternalReference()!=null && individualRequest.getExternalReference().size()>0){
            LOGGER.info("Saving external references...");
            saveExternalReferences(individualRequest.getExternalReference(), individualId, null);
        }
        if(individualRequest.getIndividualIdentification()!=null && individualRequest.getIndividualIdentification().size()>0){
            LOGGER.info("Saving individual identifications...");
            saveIndividualOrganizationIdentifications(individualRequest.getIndividualIdentification(), individualId, null);
        }
        if(individualRequest.getLanguageAbility()!=null && individualRequest.getLanguageAbility().size()>0){
            LOGGER.info("Saving language abilities...");
            saveLanguageAbilities(individualRequest.getLanguageAbility(), individualId);
        }
        if(individualRequest.getOtherName()!=null && individualRequest.getOtherName().size()>0){
            LOGGER.info("Saving other names...");
            saveOtherNames(individualRequest.getOtherName(), individualId, null);
        }
        if(individualRequest.getPartyCharacteristic()!=null && individualRequest.getPartyCharacteristic().size()>0){
            LOGGER.info("Saving party characteristics...");
            savePartyCharacteristics(individualRequest.getPartyCharacteristic(), individualId, null);
        }
        if(individualRequest.getRelatedParty()!=null && individualRequest.getRelatedParty().size()>0){
            LOGGER.info("Saving related parties...");
            saveRelatedParties(individualRequest.getRelatedParty(), individualId, null);
        }
        if(individualRequest.getSkill()!=null && individualRequest.getSkill().size()>0){
            LOGGER.info("Saving skills...");
            saveSkills(individualRequest.getSkill(), individualId);
        }
        if(individualRequest.getTaxExemptionCertificate()!=null && individualRequest.getTaxExemptionCertificate().size()>0){
            LOGGER.info("Saving tax exemption certificates...");
            saveTaxExemptionCertificates(individualRequest.getTaxExemptionCertificate(), individualId, null);
        }

        IndividualResponse individualResponse = new IndividualResponse();
        BeanUtils.copyProperties(individualRequest, individualResponse);
        individualResponse.setId(individualId);

        return individualResponse;
    }

    @Override
    public IndividualResponse updateIndividual(String individualId, IndividualRequest individualRequest) throws Exception {
        Individual individual = individualDaoReadRepository.getIndividual(individualId);
        if(individual==null){
            throw new NotFoundException("Individual is not found with this id!");
        }

        Map<String, String> map = new HashMap<>();
        if(StringUtils.hasLength(individualRequest.getAristocraticTitle())){
            map.put("aristocratic_title", "'"+individualRequest.getAristocraticTitle()+"'");
        }
        if(individualRequest.getBirthDate()!=null){
            map.put("birth_date", "'"+individualRequest.getBirthDate().toString()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getCountryOfBirth())){
            map.put("country_of_birth", "'"+individualRequest.getCountryOfBirth()+"'");
        }
        if(individualRequest.getDeathDate()!=null){
            map.put("death_date", "'"+individualRequest.getDeathDate().toString()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getFamilyName())){
            map.put("family_name", "'"+individualRequest.getFamilyName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getFamilyNamePrefix())){
            map.put("family_name_prefix", "'"+individualRequest.getFamilyNamePrefix()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getFormattedName())){
            map.put("formatted_name", "'"+individualRequest.getFormattedName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getFullName())){
            map.put("full_name", "'"+individualRequest.getFullName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getGender())){
            map.put("gender", "'"+individualRequest.getGender()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getGeneration())){
            map.put("generation", "'"+individualRequest.getGeneration()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getGivenName())){
            map.put("given_name", "'"+individualRequest.getGivenName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getLegalName())){
            map.put("legal_name", "'"+individualRequest.getLegalName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getLocation())){
            map.put("location", "'"+individualRequest.getLocation()+"'");
        }

        if(StringUtils.hasLength(individualRequest.getMaritalStatus())){
            map.put("marital_status", "'"+individualRequest.getMaritalStatus()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getMiddleName())){
            map.put("middle_name", "'"+individualRequest.getMiddleName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getNationality())){
            map.put("nationality", "'"+individualRequest.getNationality()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getPlaceOfBirth())){
            map.put("place_of_birth", "'"+individualRequest.getPlaceOfBirth()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getPreferredGivenName())){
            map.put("preferred_given_name", "'"+individualRequest.getPreferredGivenName()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getTitle())){
            map.put("title", "'"+individualRequest.getTitle()+"'");
        }
        if(StringUtils.hasLength(individualRequest.getStatus())){
            map.put("status", "'"+individualRequest.getStatus()+"'");
        }

        individualDaoWriteRepository.updateIndividualById(individualId, map);

        if(individualRequest.getContactMedium()!=null && individualRequest.getContactMedium().size()>0){
            LOGGER.info("Deleting contact medium...");
            this.deleteContactMediumByIndividualId(individualId);

            LOGGER.info("Saving contact medium...");
            saveContactMedium(individualRequest.getContactMedium(), individualId, null);
        }
        if(individualRequest.getCreditRating()!=null && individualRequest.getCreditRating().size()>0){
            this.deleteCreditRating(individualId);

            LOGGER.info("Saving credit rating...");
            saveCreditRatings(individualRequest.getCreditRating(), individualId, null);
        }
        if(individualRequest.getDisability()!=null && individualRequest.getDisability().size()>0){
            this.deleteDisability(individualId);

            LOGGER.info("Saving disabilities...");
            saveDisabilities(individualRequest.getDisability(), individualId);
        }
        if(individualRequest.getExternalReference()!=null && individualRequest.getExternalReference().size()>0){
            this.deleteExternalReferences(individualId);

            LOGGER.info("Saving external references...");
            saveExternalReferences(individualRequest.getExternalReference(), individualId, null);
        }
        if(individualRequest.getIndividualIdentification()!=null && individualRequest.getIndividualIdentification().size()>0){
            this.deleteIndividualIdentification(individualId);

            LOGGER.info("Saving individual identifications...");
            saveIndividualOrganizationIdentifications(individualRequest.getIndividualIdentification(), individualId, null);
        }
        if(individualRequest.getLanguageAbility()!=null && individualRequest.getLanguageAbility().size()>0){
            this.deleteLanguageAbility(individualId);

            LOGGER.info("Saving language abilities...");
            saveLanguageAbilities(individualRequest.getLanguageAbility(), individualId);
        }
        if(individualRequest.getOtherName()!=null && individualRequest.getOtherName().size()>0){
            this.deleteOtherName(individualId);

            LOGGER.info("Saving other names...");
            saveOtherNames(individualRequest.getOtherName(), individualId, null);
        }
        if(individualRequest.getPartyCharacteristic()!=null && individualRequest.getPartyCharacteristic().size()>0){
            this.deletePartyCharacteristic(individualId);

            LOGGER.info("Saving party characteristics...");
            savePartyCharacteristics(individualRequest.getPartyCharacteristic(), individualId, null);
        }
        if(individualRequest.getRelatedParty()!=null && individualRequest.getRelatedParty().size()>0){
            this.deleteRelatedParty(individualId);

            LOGGER.info("Saving related parties...");
            saveRelatedParties(individualRequest.getRelatedParty(), individualId, null);
        }
        if(individualRequest.getSkill()!=null && individualRequest.getSkill().size()>0){
            this.deleteSkill(individualId);

            LOGGER.info("Saving skills...");
            saveSkills(individualRequest.getSkill(), individualId);
        }
        if(individualRequest.getTaxExemptionCertificate()!=null && individualRequest.getTaxExemptionCertificate().size()>0){
            this.deleteTaxExemptionCertificate(individualId);

            LOGGER.info("Saving tax exemption certificates...");
            saveTaxExemptionCertificates(individualRequest.getTaxExemptionCertificate(), individualId, null);
        }

        IndividualResponse individualResponse = this.getIndividualById(individualId);

        return individualResponse;
    }
    @Override
    public void saveContactMedium(List<ContactMediumRequest> contactMediumRequestList, String individualId, String organizationId){
        List<ContactMedium> contactMediumList = new ArrayList<>();

        for(ContactMediumRequest contactMediumRequest: contactMediumRequestList){

            ContactMedium contactMedium = new ContactMedium();
            String contactMediumId = individualDaoWriteRepository.getNextSequenceValue(Defs.CONTACT_MEDIUM_ID_SEQUENCE).toString();

            contactMediumRequest.setId(contactMediumId);
            BeanUtils.copyProperties(contactMediumRequest, contactMedium);

            //save characteristic
            String characteristicId = this.saveMediumCharacteristic(contactMediumRequest.getCharacteristic());
            contactMedium.setCharacteristicId(characteristicId);

            //save valid for
            if(contactMediumRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(contactMediumRequest.getValidFor());
                contactMedium.setValidForId(id);
                contactMediumRequest.getValidFor().setId(id);
            }

            contactMedium.setIndividualId(individualId);
            contactMedium.setOrganizationId(organizationId);
            contactMediumList.add(contactMedium);
        }
        individualDaoWriteRepository.saveContactMedium(contactMediumList);
    }
    public String saveMediumCharacteristic(MediumCharacteristic mediumCharacteristic){
        String characteristicId =  individualDaoWriteRepository.getNextSequenceValue(Defs.MEDIUM_CHARACTERISTIC_ID_SEQUENCE).toString();
        mediumCharacteristic.setId(characteristicId);
        individualDaoWriteRepository.saveMediumCharacteristic(mediumCharacteristic);
        return characteristicId;

    }
    @Override
    public void saveCreditRatings(List<CreditRatingRequest> creditRatingRequestList, String individualId, String organizationId){
        List<CreditRating> creditRatingList = new ArrayList<>();

        for(CreditRatingRequest creditRatingRequest: creditRatingRequestList){
            CreditRating creditRating = new CreditRating();
            String creditRatingProfileId = individualDaoWriteRepository.getNextSequenceValue(Defs.PARTY_CREDIT_PROFILE_ID_SEQUENCE).toString();
            creditRatingRequest.setId(creditRatingProfileId);
            BeanUtils.copyProperties(creditRatingRequest, creditRating);

            //save valid for
            if(creditRatingRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(creditRatingRequest.getValidFor());
                creditRating.setValidForId(id);
                creditRatingRequest.getValidFor().setId(id);
            }

            creditRating.setIndividualId(individualId);
            creditRating.setOrganizationId(organizationId);
            creditRatingList.add(creditRating);
        }
        individualDaoWriteRepository.saveCreditRating(creditRatingList);
    }

    public void saveDisabilities(List<DisabilityRequest> disabilityRequestList, String individualId){
        List<Disability> disabilityList = new ArrayList<>();

        for(DisabilityRequest disabilityRequest: disabilityRequestList){
            Disability disability = new Disability();
            String disabilityId = individualDaoWriteRepository.getNextSequenceValue(Defs.DISABILITY_ID_SEQUENCE).toString();
            disabilityRequest.setId(disabilityId);
            BeanUtils.copyProperties(disabilityRequest, disability);

            //save valid for
            if(disabilityRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(disabilityRequest.getValidFor());
                disability.setValidForId(id);
                disabilityRequest.getValidFor().setId(id);
            }
            disability.setIndividualId(individualId);
            disabilityList.add(disability);
        }
        individualDaoWriteRepository.saveDisabilities(disabilityList);

    }

    @Override
    public void saveExternalReferences(List<ExternalReference> externalReferenceList, String individualId, String organizationId){
        for(ExternalReference externalReference: externalReferenceList){
            String externalReferenceId = individualDaoWriteRepository.getNextSequenceValue(Defs.EXTERNAL_REFERENCE_ID_SEQUENCE).toString();
            externalReference.setId(externalReferenceId);
            externalReference.setIndividualId(individualId);
            externalReference.setOrganizationId(organizationId);
        }
        individualDaoWriteRepository.saveExternalReferences(externalReferenceList);
    }

    @Override
    public void saveIndividualOrganizationIdentifications(List<IndividualOrganizationIdentificationRequest> individualOrganizationIdentificationRequestList, String individualId, String organizationId){
        List<IndividualOrganizationIdentification> individualOrganizationIdentificationList = new ArrayList<>();

        for(IndividualOrganizationIdentificationRequest individualOrganizationIdentificationRequest : individualOrganizationIdentificationRequestList){
            IndividualOrganizationIdentification individualOrganizationIdentification = new IndividualOrganizationIdentification();
            String individualIdentificationId = individualDaoWriteRepository.getNextSequenceValue(Defs.INDIVIDUAL_ORGANIZATION_IDENTIFICATION_ID_SEQUENCE).toString();
            individualOrganizationIdentificationRequest.setIdentificationId(individualIdentificationId);
            BeanUtils.copyProperties(individualOrganizationIdentificationRequest, individualOrganizationIdentification);

            Attachment attachment = this.saveAttachment(individualOrganizationIdentificationRequest.getAttachment());
            individualOrganizationIdentification.setAttachmentId(attachment.getId());
            individualOrganizationIdentificationRequest.getAttachment().getValidFor().setId(attachment.getValidForId());
            individualOrganizationIdentificationRequest.getAttachment().getSize().setId(Long.valueOf(attachment.getSizeId()));

            //save valid for
            if(individualOrganizationIdentificationRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(individualOrganizationIdentificationRequest.getValidFor());
                individualOrganizationIdentification.setValidForId(id);
                individualOrganizationIdentificationRequest.getValidFor().setId(id);
            }
            individualOrganizationIdentification.setIndividualId(individualId);
            individualOrganizationIdentification.setOrganizationId(organizationId);

            individualOrganizationIdentificationList.add(individualOrganizationIdentification);
        }
        individualDaoWriteRepository.saveIndividualIdentification(individualOrganizationIdentificationList);
    }
    public void saveLanguageAbilities(List<LanguageAbilityRequest> languageAbilityRequestList, String individualId){

        List<LanguageAbility> languageAbilityList = new ArrayList<>();

        for(LanguageAbilityRequest languageAbilityRequest: languageAbilityRequestList){
            LanguageAbility languageAbility = new LanguageAbility();
            String languageAbilityId = individualDaoWriteRepository.getNextSequenceValue(Defs.LANGUAGE_ABILITY_ID_SEQ).toString();
            languageAbilityRequest.setId(languageAbilityId);
            BeanUtils.copyProperties(languageAbilityRequest, languageAbility);
            languageAbility.setFavouriteLanguage(languageAbilityRequest.isFavouriteLanguage());

            //save valid for
            if(languageAbilityRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(languageAbilityRequest.getValidFor());
                languageAbility.setValidForId(id);
                languageAbilityRequest.getValidFor().setId(id);
            }

            languageAbility.setIndividualId(individualId);
            languageAbilityList.add(languageAbility);
        }
        individualDaoWriteRepository.saveLanguageAbility(languageAbilityList);

    }
    @Override
    public void saveOtherNames(List<OtherNameIndividualRequest> otherNameIndividualRequestList, String individualId, String organizationId){
        List<OtherNameIndividual> otherNameIndividualList = new ArrayList<>();

        for(OtherNameIndividualRequest otherNameIndividualRequest : otherNameIndividualRequestList){
            OtherNameIndividual otherNameIndividual = new OtherNameIndividual();

            String otherNameId = individualDaoWriteRepository.getNextSequenceValue(Defs.OTHER_NAME_INDIVIDUAL_ID_SEQUENCE).toString();
            otherNameIndividualRequest.setId(otherNameId);
            BeanUtils.copyProperties(otherNameIndividualRequest, otherNameIndividual);

            //save valid for
            if(otherNameIndividualRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(otherNameIndividualRequest.getValidFor());
                otherNameIndividual.setValidForId(id);
                otherNameIndividualRequest.getValidFor().setId(id);
            }
            otherNameIndividual.setIndividualId(individualId);
            otherNameIndividualList.add(otherNameIndividual);
        }
        individualDaoWriteRepository.saveOtherNameIndividual(otherNameIndividualList);
    }
    @Override
    public void savePartyCharacteristics(List<PartyCharacteristic> partyCharacteristicList, String individualId, String organizationId){
        for(PartyCharacteristic partyCharacteristic: partyCharacteristicList){
            String partyCharacteristicId = individualDaoWriteRepository.getNextSequenceValue(Defs.PARTY_CHARACTERISTIC_ID_SEQ).toString();
            partyCharacteristic.setId(partyCharacteristicId);
            partyCharacteristic.setIndividualId(individualId);
            partyCharacteristic.setOrganizationId(organizationId);
        }
        individualDaoWriteRepository.savePartyCharacteristic(partyCharacteristicList);
    }

    @Override
    public void saveRelatedParties(List<RelatedParty> relatedPartyList, String individualId, String organizationId){

        for(RelatedParty relatedParty: relatedPartyList){
            String relatedPartyId = individualDaoWriteRepository.getNextSequenceValue(Defs.RELATED_PARTY_ID_SEQUENCE).toString();
            relatedParty.setId(relatedPartyId);
            relatedParty.setIndividualId(individualId);
            relatedParty.setOrganizationId(organizationId);
        }
        individualDaoWriteRepository.saveRelatedPartyList(relatedPartyList);
    }

    @Override
    public void saveTaxExemptionCertificates(List<TaxExemptionCertificateRequest> taxExemptionCertificateRequestList, String individualId, String organizationId){
        List<TaxExemptionCertificate> taxExemptionCertificateList = new ArrayList<>();
        for(TaxExemptionCertificateRequest taxExemptionCertificateRequest: taxExemptionCertificateRequestList){
            TaxExemptionCertificate taxExemptionCertificate = new TaxExemptionCertificate();
            String taxExemptionCertificateId = individualDaoWriteRepository.getNextSequenceValue(Defs.TAX_EXEMPTION_CERTIFICATE_ID_SEQUENCE).toString();
            taxExemptionCertificateRequest.setId(taxExemptionCertificateId);
            BeanUtils.copyProperties(taxExemptionCertificateRequest, taxExemptionCertificate);

            Attachment attachment = this.saveAttachment(taxExemptionCertificateRequest.getAttachment());
            taxExemptionCertificate.setAttachmentId(attachment.getId());

            taxExemptionCertificateRequest.getAttachment().getValidFor().setId(attachment.getValidForId());
            taxExemptionCertificateRequest.getAttachment().getSize().setId(Long.valueOf(attachment.getSizeId()));

            //save valid for
            if(taxExemptionCertificateRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(taxExemptionCertificateRequest.getValidFor());
                taxExemptionCertificate.setValidForId(id);

                taxExemptionCertificateRequest.getValidFor().setId(id);
            }

            taxExemptionCertificate.setIndividualId(individualId);
            taxExemptionCertificate.setOrganizationId(organizationId);
            taxExemptionCertificateList.add(taxExemptionCertificate);
        }
        individualDaoWriteRepository.saveTaxExemptionCertificate(taxExemptionCertificateList);

        for(TaxExemptionCertificateRequest taxExemptionCertificateRequest: taxExemptionCertificateRequestList){
            if(taxExemptionCertificateRequest.getTaxDefinition()!=null && taxExemptionCertificateRequest.getTaxDefinition().size()>0){
                saveTaxDefinition(taxExemptionCertificateRequest.getTaxDefinition(), taxExemptionCertificateRequest.getId());
            }
        }
    }

    @Override
    public IndividualResponse getIndividualById(String id) throws Exception{
        IndividualResponse individualResponse = null;

        Individual individual = individualDaoReadRepository.getIndividual(id);
        if(individual!=null){
            individualResponse = new IndividualResponse();
            BeanUtils.copyProperties(individual, individualResponse);

            //get contact medium
            individualResponse.setContactMedium(getContactMedium(id));

            //get credit rating
            individualResponse.setCreditRating(getCreditRating(id));

            //get disability
            individualResponse.setDisability(getDisability(id));

            //get External reference
            individualResponse.setExternalReference(individualDaoReadRepository.getExternalReferenceForIndividual(individual.getId()));

            // get IndividualIdentification
            individualResponse.setIndividualIdentification(getIndividualIdentification(id));

            //get language ability
            individualResponse.setLanguageAbility(getLanguageAbility(id));

            //get other name
            individualResponse.setOtherName(getOtherName(id));

            //get party characteristic
            individualResponse.setPartyCharacteristic(individualDaoReadRepository.getPartyCharacteristicForIndividual(id));

            //get related party
            individualResponse.setRelatedParty(individualDaoReadRepository.getRelatedPartyForIndividual(id));

            //get skill
            individualResponse.setSkill(getSkill(id));

            //get taxExemptionCertificate;
            individualResponse.setTaxExemptionCertificate(getTaxExemptionCertificate(id));

        }else{
            throw new NotFoundException("Individual is not found with this id");
        }
        return individualResponse;
    }

    @Override
    public List<IndividualResponse> getPaginatedIndividual(Map<String, Object> requestParams, Integer limit, Integer offset) throws Exception {
        List<String> individualIdList = individualDaoReadRepository.getPaginatedIndividual(limit, offset);

        List<IndividualResponse> individualResponseList = new ArrayList<>();
        for(String individualId: individualIdList){
            IndividualResponse individualResponse = this.getIndividualById(individualId);
            individualResponseList.add(individualResponse);
        }
        return individualResponseList;
    }
    @Override
    public String deleteIndividual(String individualId) throws Exception{
        //delete contact medium
        deleteContactMediumByIndividualId(individualId);

        //delete credit rating
        deleteCreditRating(individualId);

        //delete disability
        deleteDisability(individualId);

        //DELETE EXTERNAL REFERENCE
        deleteExternalReferences(individualId);

        //delete Individual Identification
        deleteIndividualIdentification(individualId);

        //delete Language Ability
        deleteLanguageAbility(individualId);

        //delete other name
        deleteOtherName(individualId);

        //DELETE party characteristic
        deletePartyCharacteristic(individualId);

        //delete related party
        deleteRelatedParty(individualId);

        //delete skill
        deleteSkill(individualId);

        //delete TaxExemptionCertificate
        deleteTaxExemptionCertificate(individualId);

        Boolean result = individualDaoWriteRepository.deleteIndividualById(individualId);

        if(result){
            return "Deleted";
        }else{
            throw new NotFoundException("Individual is not found with this id!");
        }
    }
    public void saveSkills(List<SkillRequest> skillRequestList, String individualId){
        List<Skill> skillList = new ArrayList<>();

        for(SkillRequest skillRequest: skillRequestList){
            Skill skill = new Skill();
            String skillId = individualDaoWriteRepository.getNextSequenceValue(Defs.SKILL_ID_SEQUENCE).toString();
            skillRequest.setId(skillId);
            BeanUtils.copyProperties(skillRequest, skill);

            //save valid for
            if(skillRequest.getValidFor()!=null){
                Long id = individualDaoWriteRepository.saveValidFor(skillRequest.getValidFor());
                skill.setValidForId(id);
                skillRequest.getValidFor().setId(id);
            }
            skill.setIndividualId(individualId);
            skillList.add(skill);
        }
        individualDaoWriteRepository.saveSkills(skillList);

    }
    public void saveTaxDefinition(List<TaxDefinition> taxDefinitionList, String taxExemptionCertificateId){
        for(TaxDefinition taxDefinition: taxDefinitionList){
            String taxDefinitionId = individualDaoWriteRepository.getNextSequenceValue(Defs.TAX_DEFINITION_ID_SEQUENCE).toString();
            taxDefinition.setId(taxDefinitionId);
            taxDefinition.setTaxExemptionCertificateId(taxExemptionCertificateId);
        }
        individualDaoWriteRepository.saveTaxDefinition(taxDefinitionList);
    }

    public String saveSize(Size size){
        return individualDaoWriteRepository.saveSize(size);
    }

    public Attachment saveAttachment(AttachmentRequest attachmentRequest){
        String attachmentId = individualDaoWriteRepository.getNextSequenceValue(Defs.ATTACHMENT_REF_ID_SEQUENCE).toString();
        attachmentRequest.setId(attachmentId);

        Attachment attachment = new Attachment();
        BeanUtils.copyProperties(attachmentRequest, attachment);
        attachment.setSizeId(this.saveSize(attachmentRequest.getSize()));

        Long id = individualDaoWriteRepository.saveValidFor(attachmentRequest.getValidFor());
        attachment.setValidForId(id);
        attachment.setReferredType(attachmentRequest.getReferredType());

        individualDaoWriteRepository.saveAttachment(attachment);
        return attachment;
    }

    private List<ContactMediumRequest> getContactMedium(String id){
        List<ContactMedium> contactMediumList = individualDaoReadRepository.getContactMediumForIndividual(id);
        List<ContactMediumRequest> contactMediumRequestList = new ArrayList<>();

        for(ContactMedium contactMedium: contactMediumList){
            ContactMediumRequest contactMediumRequest = new ContactMediumRequest();
            BeanUtils.copyProperties(contactMedium, contactMediumRequest);
            contactMediumRequest.setBaseType(contactMedium.getBaseType());
            contactMediumRequest.setSchemaLocation(contactMedium.getSchemaLocation());
            contactMediumRequest.setType(contactMedium.getType());

            //get medium characteristic
            contactMediumRequest.setCharacteristic(individualDaoReadRepository.getMediumCharacteristic(contactMedium.getId()));

            //get valid for
            if(contactMedium.getValidForId()!=null){
                contactMediumRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(contactMedium.getValidForId())));
            }

            contactMediumRequestList.add(contactMediumRequest);
        }
        return contactMediumRequestList;
    }
    private List<CreditRatingRequest> getCreditRating(String id){
        List<CreditRating> creditRatingList = individualDaoReadRepository.getCreditRatingForIndividual(id);
        List<CreditRatingRequest> creditRatingRequestList = new ArrayList<>();

        for(CreditRating creditRating: creditRatingList){
            CreditRatingRequest creditRatingRequest = new CreditRatingRequest();
            BeanUtils.copyProperties(creditRating, creditRatingRequest);
            creditRatingRequest.setBaseType(creditRating.getBaseType());
            creditRatingRequest.setSchemaLocation(creditRating.getSchemaLocation());
            creditRatingRequest.setType(creditRating.getType());

            if(creditRating.getValidForId()!=null){
                creditRatingRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(creditRating.getValidForId())));
            }
            creditRatingRequestList.add(creditRatingRequest);
        }
        return creditRatingRequestList;
    }
    private List<DisabilityRequest> getDisability(String id){
        List<Disability> disabilityList = individualDaoReadRepository.getDisabilityByIndividualId(id);
        List<DisabilityRequest> disabilityRequestList = new ArrayList<>();

        for(Disability disability: disabilityList){
            DisabilityRequest disabilityRequest = new DisabilityRequest();
            BeanUtils.copyProperties(disability, disabilityRequest);

            if(disability.getValidForId()!=null){
                disabilityRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(disability.getValidForId())));
            }
            disabilityRequestList.add(disabilityRequest);
        }
        return disabilityRequestList;
    }
    private List<IndividualOrganizationIdentificationRequest> getIndividualIdentification(String id){
        List<IndividualOrganizationIdentification> individualOrganizationIdentificationList = individualDaoReadRepository.getIndividualIdentification(id);
        List<IndividualOrganizationIdentificationRequest> identificationRequestList = new ArrayList<>();

        for(IndividualOrganizationIdentification individualOrganizationIdentification : individualOrganizationIdentificationList){
            IndividualOrganizationIdentificationRequest individualOrganizationIdentificationRequest = new IndividualOrganizationIdentificationRequest();
            BeanUtils.copyProperties(individualOrganizationIdentification, individualOrganizationIdentificationRequest);

            //get attachment
            individualOrganizationIdentificationRequest.setAttachment(getAttachmentRequest(individualOrganizationIdentification.getAttachmentId()));
            if(individualOrganizationIdentification.getValidForId()!=null){
                individualOrganizationIdentificationRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(individualOrganizationIdentification.getValidForId())));
            }
            identificationRequestList.add(individualOrganizationIdentificationRequest);
        }
        return identificationRequestList;
    }
    private List<LanguageAbilityRequest> getLanguageAbility(String id){
        List<LanguageAbility> languageAbilityList = individualDaoReadRepository.getLanguageAbilityForIndividual(id);
        List<LanguageAbilityRequest> languageAbilityRequestList = new ArrayList<>();

        for(LanguageAbility languageAbility: languageAbilityList){
            LanguageAbilityRequest languageAbilityRequest = new LanguageAbilityRequest();
            BeanUtils.copyProperties(languageAbility, languageAbilityRequest);
            if(languageAbility.getValidForId()!=null){
                languageAbilityRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(languageAbility.getValidForId())));
            }
            languageAbilityRequestList.add(languageAbilityRequest);
        }
        return languageAbilityRequestList;
    }
    private List<OtherNameIndividualRequest> getOtherName(String id){
        List<OtherNameIndividual> otherNameIndividualList = individualDaoReadRepository.getOtherNameForIndividual(id);
        List<OtherNameIndividualRequest> otherNameIndividualRequestList = new ArrayList<>();

        for(OtherNameIndividual otherNameIndividual : otherNameIndividualList){
            OtherNameIndividualRequest otherNameIndividualRequest = new OtherNameIndividualRequest();
            BeanUtils.copyProperties(otherNameIndividual, otherNameIndividualRequest);
            if(otherNameIndividual.getValidForId()!=null){
                otherNameIndividualRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(otherNameIndividual.getValidForId())));
            }
            otherNameIndividualRequestList.add(otherNameIndividualRequest);
        }
        return otherNameIndividualRequestList;
    }
    private List<SkillRequest> getSkill(String id){
        List<Skill> skillList = individualDaoReadRepository.getSkillForIndividual(id);
        List<SkillRequest> skillRequestList = new ArrayList<>();

        for(Skill skill: skillList){
            SkillRequest skillRequest = new SkillRequest();
            BeanUtils.copyProperties(skill, skillRequest);
            if(skill.getValidForId()!=null){
                skillRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(skill.getValidForId())));
            }
            skillRequestList.add(skillRequest);
        }
        return skillRequestList;
    }
    private  List<TaxExemptionCertificateRequest> getTaxExemptionCertificate(String id){
        List<TaxExemptionCertificate> taxExemptionCertificateListList = individualDaoReadRepository.getTaxExemptionCertificateForIndividual(id);
        List<TaxExemptionCertificateRequest> taxExemptionCertificateRequestList = new ArrayList<>();

        for(TaxExemptionCertificate taxExemptionCertificate: taxExemptionCertificateListList){
            TaxExemptionCertificateRequest taxExemptionCertificateRequest = new TaxExemptionCertificateRequest();
            BeanUtils.copyProperties(taxExemptionCertificate, taxExemptionCertificateRequest);

            //get attachment
            taxExemptionCertificateRequest.setAttachment(getAttachmentRequest(taxExemptionCertificate.getAttachmentId()));

            //get tax definition
            taxExemptionCertificateRequest.setTaxDefinition(individualDaoReadRepository.getTaxDefinition(taxExemptionCertificate.getId()));

            if(taxExemptionCertificate.getValidForId()!=null){
                taxExemptionCertificateRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(taxExemptionCertificate.getValidForId())));
            }
            taxExemptionCertificateRequestList.add(taxExemptionCertificateRequest);
        }
        return taxExemptionCertificateRequestList;
    }
    private AttachmentRequest getAttachmentRequest(String attachmentId){
        Attachment attachment = individualDaoReadRepository.getAttachment(attachmentId);
        AttachmentRequest attachmentRequest = new AttachmentRequest();
        BeanUtils.copyProperties(attachment, attachmentRequest);

        //get valid for the attachment
        attachmentRequest.setValidFor(individualDaoReadRepository.getValidFor(String.valueOf(attachment.getValidForId())));

        //get size for the attachment
        attachmentRequest.setSize(individualDaoReadRepository.getSize(attachment.getSizeId()));

        return attachmentRequest;
    }
    private void deleteContactMediumByIndividualId(String individualId){
        List<ContactMedium> contactMediumList = individualDaoReadRepository.getContactMediumForIndividual(individualId);
        if(contactMediumList!=null && contactMediumList.size()> 0){
            List<String> mediumCHaracteristicIdlist = new ArrayList<>();
            List<String> validForIdLIstForContactMedium = new ArrayList<>();
            for(ContactMedium contactMedium: contactMediumList){
                if(contactMedium.getCharacteristicId()!=null){
                    mediumCHaracteristicIdlist.add(contactMedium.getCharacteristicId());
                }
                if(contactMedium.getValidForId()!=null){
                    validForIdLIstForContactMedium.add(String.valueOf(contactMedium.getValidForId()));
                }
            }
            if(mediumCHaracteristicIdlist.size()>0){
                individualDaoWriteRepository.deleteMediumCharacteristicByIdList(validForIdLIstForContactMedium);
            }
            if(validForIdLIstForContactMedium.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdLIstForContactMedium);
            }
            individualDaoWriteRepository.deleteContactMediumByIndividualId(individualId);
        }
    }
    private void deleteCreditRating(String individualId){
        List<CreditRating> creditRatingList = individualDaoReadRepository.getCreditRatingForIndividual(individualId);
        if(creditRatingList!=null && creditRatingList.size()>0){
            List<String> validForIdListForCreditRating = creditRatingList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());
            if(validForIdListForCreditRating!=null && validForIdListForCreditRating.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForCreditRating);
            }
            individualDaoWriteRepository.deleteCreditRatingByIndividualId(individualId);
        }
    }

    private void deleteDisability(String individualId){
        List<Disability> disabilityList = individualDaoReadRepository.getDisabilityByIndividualId(individualId);
        if(disabilityList!=null && disabilityList.size()>0){
            List<String> validForIdListForDisability = disabilityList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());

            if (validForIdListForDisability!=null && validForIdListForDisability.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForDisability);
            }
            individualDaoWriteRepository.deleteDisabilityByIndividualId(individualId);
        }
    }

    private void deleteIndividualIdentification(String individualId){
        List<IndividualOrganizationIdentification> individualIdentificationList = individualDaoReadRepository.getIndividualIdentification(individualId);
        if(individualIdentificationList!=null && individualIdentificationList.size()>0){
            List<String> validForIdListForIndividualIdentification = individualIdentificationList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());
            if(validForIdListForIndividualIdentification!=null && validForIdListForIndividualIdentification.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForIndividualIdentification);
            }
            individualDaoWriteRepository.deleteIndividualIdentificationByIndividualId(individualId);
        }
    }
    private void deleteLanguageAbility(String individualId){
        List<LanguageAbility> languageAbilityList = individualDaoReadRepository.getLanguageAbilityForIndividual(individualId);
        if(languageAbilityList!=null && languageAbilityList.size()>0){
            List<String> validForIdListForLanguageAbility = languageAbilityList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());

            if(validForIdListForLanguageAbility!=null && validForIdListForLanguageAbility.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForLanguageAbility);
            }
            individualDaoWriteRepository.deleteLanguageAbilityByIndividualId(individualId);
        }
    }
    private void deleteOtherName(String individualId){
        List<OtherNameIndividual> otherNameList = individualDaoReadRepository.getOtherNameForIndividual(individualId);
        if(otherNameList!=null && otherNameList.size()>0){
            List<String> validForIdListForOtherName = otherNameList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());
            if(validForIdListForOtherName!=null && validForIdListForOtherName.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForOtherName);
            }
            individualDaoWriteRepository.deleteOtherNameByIndividualId(individualId);
        }
    }
    private void deleteSkill(String individualId){
        List<Skill> skillList = individualDaoReadRepository.getSkillForIndividual(individualId);
        if(skillList!=null && skillList.size()>0){
            List<String> validForIdListForSkill = skillList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());

            if(validForIdListForSkill!=null && validForIdListForSkill.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForSkill);
            }
            individualDaoWriteRepository.deleteSkillByIndividualId(individualId);
        }
    }
    private void deleteTaxExemptionCertificate(String individualId){
        List<TaxExemptionCertificate> taxExemptionCertificateList = individualDaoReadRepository.getTaxExemptionCertificateForIndividual(individualId);
        if(taxExemptionCertificateList!=null && taxExemptionCertificateList.size()>0){
            List<String> validForIdListForTaxExemptionCertificate = new ArrayList<>();
            List<String> attachmentIdListForTaxExemptionCertificate = new ArrayList<>();
            List<String> taxExemptionCertificateIdList = new ArrayList<>();

            for(TaxExemptionCertificate taxExemptionCertificate: taxExemptionCertificateList){
                if(taxExemptionCertificate.getValidForId()!=null){
                    validForIdListForTaxExemptionCertificate.add(String.valueOf(taxExemptionCertificate.getValidForId()));
                }
                if(taxExemptionCertificate.getAttachmentId()!=null){
                    attachmentIdListForTaxExemptionCertificate.add(taxExemptionCertificate.getAttachmentId());
                }
                taxExemptionCertificateIdList.add("'"+taxExemptionCertificate.getId()+"'");
            }
            if(validForIdListForTaxExemptionCertificate.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForTaxExemptionCertificate);
            }
            individualDaoWriteRepository.deleteTaxDefinitionById(taxExemptionCertificateIdList);
            individualDaoWriteRepository.deleteTaxExemptionCertificateByIndividualId(individualId);
            if(attachmentIdListForTaxExemptionCertificate.size()>0){
                individualDaoWriteRepository.deleteAttachmentByIdList(attachmentIdListForTaxExemptionCertificate);
            }
        }
    }
    private void deleteExternalReferences(String individualId){
        individualDaoWriteRepository.deleteExternalReferenceByIndividualId(individualId);
    }
    private void deletePartyCharacteristic(String individualId){
        individualDaoWriteRepository.deletePartyCharacteristicByIndividualId(individualId);
    }

    private void deleteRelatedParty(String individualId){
        individualDaoWriteRepository.deleteRelatedPartyByIndividualId(individualId);
    }
}
