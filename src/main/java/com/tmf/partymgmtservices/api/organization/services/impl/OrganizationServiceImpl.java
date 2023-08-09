package com.tmf.partymgmtservices.api.organization.services.impl;

import com.tmf.partymgmtservices.api.exceptions.BadRequestException;
import com.tmf.partymgmtservices.api.exceptions.NotFoundException;
import com.tmf.partymgmtservices.api.individual.domain.model.*;
import com.tmf.partymgmtservices.api.individual.domain.request.*;
import com.tmf.partymgmtservices.api.individual.services.IndividualService;
import com.tmf.partymgmtservices.api.organization.domain.model.Organization;
import com.tmf.partymgmtservices.api.organization.domain.model.OrganizationChildRelationship;
import com.tmf.partymgmtservices.api.organization.domain.model.OrganizationParentRelationship;
import com.tmf.partymgmtservices.api.organization.domain.model.OtherNameOrganization;
import com.tmf.partymgmtservices.api.organization.domain.request.OrganizationChildRelationshipRequest;
import com.tmf.partymgmtservices.api.organization.domain.request.OrganizationParentRelationshipRequest;
import com.tmf.partymgmtservices.api.organization.domain.request.OrganizationRequest;
import com.tmf.partymgmtservices.api.organization.domain.request.OtherNameOrganizationRequest;
import com.tmf.partymgmtservices.api.organization.domain.response.OrganizationResponse;
import com.tmf.partymgmtservices.api.organization.services.OrganizationService;
import com.tmf.partymgmtservices.api.util.Defs;
import com.tmf.partymgmtservices.repository.db.model.IndividualDaoReadRepository;
import com.tmf.partymgmtservices.repository.db.model.IndividualDaoWriteRepository;
import com.tmf.partymgmtservices.repository.db.model.OrganizationDaoReadRepository;
import com.tmf.partymgmtservices.repository.db.model.OrganizationDaoWriteRepository;
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
public class OrganizationServiceImpl implements OrganizationService {
    private static final Logger LOGGER = LogManager.getLogger(OrganizationServiceImpl.class);

    private final OrganizationDaoWriteRepository organizationDaoWriteRepository;
    private final OrganizationDaoReadRepository organizationDaoReadRepository;
    private final IndividualService individualService;
    private final IndividualDaoReadRepository individualDaoReadRepository;
    private final IndividualDaoWriteRepository individualDaoWriteRepository;

    @Autowired
    OrganizationServiceImpl(OrganizationDaoWriteRepository organizationDaoWriteRepository,
                            OrganizationDaoReadRepository organizationDaoReadRepository,
                            IndividualService individualService,
                            IndividualDaoReadRepository individualDaoReadRepository,
                            IndividualDaoWriteRepository individualDaoWriteRepository){
        this.organizationDaoWriteRepository = organizationDaoWriteRepository;
        this.organizationDaoReadRepository = organizationDaoReadRepository;
        this.individualService = individualService;
        this.individualDaoReadRepository = individualDaoReadRepository;
        this.individualDaoWriteRepository = individualDaoWriteRepository;
    }

    @Override
    public OrganizationResponse saveOrganization(OrganizationRequest organizationRequest) throws Exception {
        if(!StringUtils.hasLength(organizationRequest.getTradingName())){
            throw new BadRequestException("Trading Name must be provided!");
        }
        Organization organization = new Organization();
        String organizationId = organizationDaoWriteRepository.getNextSequenceValue(Defs.ORGANIZATION_ID_SEQUENCE).toString();
        organizationRequest.setId(organizationId);
        BeanUtils.copyProperties(organizationRequest, organization);

        Long existsDuringId = organizationDaoWriteRepository.saveValidFor(organizationRequest.getExistsDuring());
        organization.setExistsDuringId(String.valueOf(existsDuringId));
        organizationRequest.getExistsDuring().setId(existsDuringId);

        if(organizationRequest.getOrganizationParentRelationship()!=null){
            organization.setOrganizationParentRelationshipId(saveOrganizationParentRelationshipRequest(organizationRequest.getOrganizationParentRelationship()));
        }
        organizationDaoWriteRepository.saveOrganization(organization);

        if(organizationRequest.getContactMedium()!=null && organizationRequest.getContactMedium().size()>0){
            LOGGER.info("Saving contact medium...");
            individualService.saveContactMedium(organizationRequest.getContactMedium(), null, organizationId);
        }
        if(organizationRequest.getCreditRating()!=null && organizationRequest.getCreditRating().size()>0){
            LOGGER.info("Saving credit rating...");
            individualService.saveCreditRatings(organizationRequest.getCreditRating(), null, organizationId);
        }

        if(organizationRequest.getExternalReference()!=null && organizationRequest.getExternalReference().size()>0){
            LOGGER.info("Saving external references...");
            individualService.saveExternalReferences(organizationRequest.getExternalReference(), null, organizationId);
        }
        if(organizationRequest.getOrganizationChildRelationship()!=null && organizationRequest.getOrganizationChildRelationship().size()>0){
            saveOrganizationChildRelationshipRequest(organizationRequest.getOrganizationChildRelationship(), organizationId);
        }

        if(organizationRequest.getOrganizationIdentification()!=null && organizationRequest.getOrganizationIdentification().size()>0){
            LOGGER.info("Saving individual identifications...");
            individualService.saveIndividualOrganizationIdentifications(organizationRequest.getOrganizationIdentification(), null, organizationId);
        }

        if(organizationRequest.getOtherName()!=null && organizationRequest.getOtherName().size()>0){
            LOGGER.info("Saving other names...");
            saveOtherNames(organizationRequest.getOtherName(), organizationId);
        }
        if(organizationRequest.getPartyCharacteristic()!=null && organizationRequest.getPartyCharacteristic().size()>0){
            LOGGER.info("Saving party characteristics...");
            individualService.savePartyCharacteristics(organizationRequest.getPartyCharacteristic(), null, organizationId);
        }
        if(organizationRequest.getRelatedParty()!=null && organizationRequest.getRelatedParty().size()>0){
            LOGGER.info("Saving related parties...");
            individualService.saveRelatedParties(organizationRequest.getRelatedParty(), null, organizationId);
        }

        if(organizationRequest.getTaxExemptionCertificate()!=null && organizationRequest.getTaxExemptionCertificate().size()>0){
            LOGGER.info("Saving tax exemption certificates...");
            individualService.saveTaxExemptionCertificates(organizationRequest.getTaxExemptionCertificate(), null, organizationId);
        }

        OrganizationResponse organizationResponse = new OrganizationResponse();
        BeanUtils.copyProperties(organizationRequest, organizationResponse);
        organizationResponse.setId(organizationId);

        return organizationResponse;
    }
    public String saveOrganizationParentRelationshipRequest(OrganizationParentRelationshipRequest organizationParentRelationshipRequest){
        OrganizationParentRelationship organizationParentRelationship = new OrganizationParentRelationship();
        String organizationParentId = organizationDaoWriteRepository.getNextSequenceValue(Defs.ORGANIZATION_PARENT_ID_RELATIONSHIP_SEQUENCE).toString();
        organizationParentRelationshipRequest.setId(organizationParentId);
        BeanUtils.copyProperties(organizationParentRelationshipRequest, organizationParentRelationship);

        if(organizationParentRelationshipRequest.getOrganization()!=null){
            String id = organizationDaoWriteRepository.saveOrganizationRef(organizationParentRelationshipRequest.getOrganization());
            organizationParentRelationship.setOrganizationRefId(id);
            organizationParentRelationshipRequest.getOrganization().setId(id);
        }
        return organizationDaoWriteRepository.saveOrganizationParentRelationship(organizationParentRelationship);
    }

    public void saveOrganizationChildRelationshipRequest(List<OrganizationChildRelationshipRequest> organizationChildRelationshipRequestList, String organizationId){
        List<OrganizationChildRelationship> organizationChildRelationshipList = new ArrayList<>();

        for(OrganizationChildRelationshipRequest organizationChildRelationshipRequest: organizationChildRelationshipRequestList){
            OrganizationChildRelationship organizationChildRelationship = new OrganizationChildRelationship();
            String organizationChildId = organizationDaoWriteRepository.getNextSequenceValue(Defs.ORGANIZATION_CHILD_RELATIONSHIP_ID_SEQUENCE).toString();
            organizationChildRelationshipRequest.setId(organizationChildId);
            BeanUtils.copyProperties(organizationChildRelationshipRequest, organizationChildRelationship);
            //save organizationRef
            if(organizationChildRelationshipRequest.getOrganization()!=null){
                String id = organizationDaoWriteRepository.saveOrganizationRef(organizationChildRelationshipRequest.getOrganization());
                organizationChildRelationship.setOrganizationRefId(id);
                organizationChildRelationshipRequest.getOrganization().setId(id);
            }
            organizationChildRelationship.setOrganizationId(organizationId);
            organizationChildRelationshipList.add(organizationChildRelationship);
        }
        organizationDaoWriteRepository.saveOrganizationChildRelationshipRequest(organizationChildRelationshipList);

    }
    public void saveOtherNames(List<OtherNameOrganizationRequest> otherNameOrganizationRequestList, String organizationId){
        List<OtherNameOrganization> otherNameOrganizationList = new ArrayList<>();
        for(OtherNameOrganizationRequest otherNameOrganizationRequest : otherNameOrganizationRequestList){
            OtherNameOrganization otherNameOrganization = new OtherNameOrganization();
            String otherNameId = organizationDaoWriteRepository.getNextSequenceValue(Defs.OTHER_NAME_ORGANIZATION_ID_SEQUENCE).toString();
            otherNameOrganizationRequest.setId(otherNameId);
            BeanUtils.copyProperties(otherNameOrganizationRequest, otherNameOrganization);
            //save valid for
            if(otherNameOrganizationRequest.getValidFor()!=null){
                Long id = organizationDaoWriteRepository.saveValidFor(otherNameOrganizationRequest.getValidFor());
                otherNameOrganization.setValidForId(id);
                otherNameOrganizationRequest.getValidFor().setId(id);
            }
            otherNameOrganization.setOrganizationId(organizationId);
            otherNameOrganizationList.add(otherNameOrganization);
        }
        organizationDaoWriteRepository.saveOtherNameOrganization(otherNameOrganizationList);
    }
    @Override
    public OrganizationResponse getOrganizationById(String id) throws Exception{
        OrganizationResponse organizationResponse = null;
        Organization organization = organizationDaoReadRepository.getOrganization(id);
        if(organization!=null){
            organizationResponse = new OrganizationResponse();
            BeanUtils.copyProperties(organization, organizationResponse);

            //get existsDuring
            organizationResponse.setExistsDuring(organizationDaoReadRepository.getValidFor(String.valueOf(organization.getExistsDuringId())));

            //get organizationParentRelationship
            organizationResponse.setOrganizationParentRelationship(getOrganizationParentRelationshipRequest(organization.getOrganizationParentRelationshipId()));

            //get contact medium
            organizationResponse.setContactMedium(getContactMedium(id));

            //get credit rating
            organizationResponse.setCreditRating(getCreditRating(id));

            //get organizationChildRelationship
            organizationResponse.setOrganizationChildRelationship(getOrganizationChildRelationshipRequest(id));

            // get IndividualIdentification
            organizationResponse.setOrganizationIdentification(getOrganizationIdentification(id));

            //get External reference
            organizationResponse.setExternalReference(individualDaoReadRepository.getExternalReferenceForOrganization(organization.getId()));

            //get other name
            organizationResponse.setOtherName(getOtherNameOrganization(id));

            //get party characteristic
            organizationResponse.setPartyCharacteristic(individualDaoReadRepository.getPartyCharacteristicForOrganization(id));

            //get related party
            organizationResponse.setRelatedParty(individualDaoReadRepository.getRelatedPartyForOrganization(id));

            //get taxExemptionCertificate;
            organizationResponse.setTaxExemptionCertificate(getTaxExemptionCertificate(id));
        }else{
            throw new NotFoundException("Organization is not found with this id!");
        }
        return organizationResponse;
    }

    @Override
    public List<OrganizationResponse> getPaginatedOrganization(Map<String, Object> requestParams, Integer limit, Integer offset) throws Exception {
        List<String> organizationIdList = organizationDaoReadRepository.getPaginatedOrganization(limit, offset);
        List<OrganizationResponse> organizationResponseList = new ArrayList<>();
        for(String organizationId: organizationIdList){
            OrganizationResponse organizationResponse = this.getOrganizationById(organizationId);
            organizationResponseList.add(organizationResponse);
        }
        return organizationResponseList;
    }

    private List<ContactMediumRequest> getContactMedium(String id){
        List<ContactMedium> contactMediumList = individualDaoReadRepository.getContactMediumForOrganization(id);
        List<ContactMediumRequest> contactMediumRequestList = new ArrayList<>();
        for(ContactMedium contactMedium: contactMediumList){
            ContactMediumRequest contactMediumRequest = new ContactMediumRequest();
            BeanUtils.copyProperties(contactMedium, contactMediumRequest);

            //get medium characteristic
            contactMediumRequest.setCharacteristic(individualDaoReadRepository.getMediumCharacteristic(contactMedium.getId()));

            //get valid for
            if(contactMedium.getValidForId()!=null){
                contactMediumRequest.setValidFor(organizationDaoReadRepository.getValidFor(String.valueOf(contactMedium.getValidForId())));
            }
            contactMediumRequestList.add(contactMediumRequest);
        }
        return contactMediumRequestList;
    }
    private List<CreditRatingRequest> getCreditRating(String id){
        List<CreditRating> creditRatingList = individualDaoReadRepository.getCreditRatingForOrganization(id);
        List<CreditRatingRequest> creditRatingRequestList = new ArrayList<>();

        for(CreditRating creditRating: creditRatingList){
            CreditRatingRequest creditRatingRequest = new CreditRatingRequest();
            BeanUtils.copyProperties(creditRating, creditRatingRequest);

            if(creditRating.getValidForId()!=null){
                creditRatingRequest.setValidFor(organizationDaoReadRepository.getValidFor(String.valueOf(creditRating.getValidForId())));
            }
            creditRatingRequestList.add(creditRatingRequest);
        }
        return creditRatingRequestList;
    }

    private List<IndividualOrganizationIdentificationRequest> getOrganizationIdentification(String id){
        List<IndividualOrganizationIdentification> individualOrganizationIdentificationList = organizationDaoReadRepository.getOrganizationIdentification(id);
        List<IndividualOrganizationIdentificationRequest> identificationRequestList = new ArrayList<>();

        for(IndividualOrganizationIdentification individualOrganizationIdentification : individualOrganizationIdentificationList){
            IndividualOrganizationIdentificationRequest individualOrganizationIdentificationRequest = new IndividualOrganizationIdentificationRequest();
            BeanUtils.copyProperties(individualOrganizationIdentification, individualOrganizationIdentificationRequest);

            //get attachment
            individualOrganizationIdentificationRequest.setAttachment(getAttachmentRequest(individualOrganizationIdentification.getAttachmentId()));

            if(individualOrganizationIdentification.getValidForId()!=null){
                individualOrganizationIdentificationRequest.setValidFor(organizationDaoReadRepository.getValidFor(String.valueOf(individualOrganizationIdentification.getValidForId())));
            }
            identificationRequestList.add(individualOrganizationIdentificationRequest);
        }
        return identificationRequestList;
    }

    private List<OtherNameOrganizationRequest> getOtherNameOrganization(String id){
        List<OtherNameOrganization> otherNameOrganizationList = organizationDaoReadRepository.getOtherNameOrganization(id);
        List<OtherNameOrganizationRequest> otherNameOrganizationRequestList = new ArrayList<>();

        for(OtherNameOrganization otherNameOrganization : otherNameOrganizationList){
            OtherNameOrganizationRequest otherNameOrganizationRequest = new OtherNameOrganizationRequest();
            BeanUtils.copyProperties(otherNameOrganization, otherNameOrganizationRequest);
            if(otherNameOrganization.getValidForId()!=null){
                otherNameOrganizationRequest.setValidFor(organizationDaoReadRepository.getValidFor(String.valueOf(otherNameOrganization.getValidForId())));
            }
            otherNameOrganizationRequestList.add(otherNameOrganizationRequest);
        }
        return otherNameOrganizationRequestList;
    }

    private  List<TaxExemptionCertificateRequest> getTaxExemptionCertificate(String id){
        List<TaxExemptionCertificate> taxExemptionCertificateListList = individualDaoReadRepository.getTaxExemptionCertificateForOrganization(id);
        List<TaxExemptionCertificateRequest> taxExemptionCertificateRequestList = new ArrayList<>();
        for(TaxExemptionCertificate taxExemptionCertificate: taxExemptionCertificateListList){
            TaxExemptionCertificateRequest taxExemptionCertificateRequest = new TaxExemptionCertificateRequest();
            BeanUtils.copyProperties(taxExemptionCertificate, taxExemptionCertificateRequest);
            //get attachment
            taxExemptionCertificateRequest.setAttachment(getAttachmentRequest(taxExemptionCertificate.getAttachmentId()));
            //get tax definition
            taxExemptionCertificateRequest.setTaxDefinition(individualDaoReadRepository.getTaxDefinition(taxExemptionCertificate.getId()));
            if(taxExemptionCertificate.getValidForId()!=null){
                taxExemptionCertificateRequest.setValidFor(organizationDaoReadRepository.getValidFor(String.valueOf(taxExemptionCertificate.getValidForId())));
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
        attachmentRequest.setValidFor(organizationDaoReadRepository.getValidFor(String.valueOf(attachment.getValidForId())));
        //get size for the attachment
        attachmentRequest.setSize(individualDaoReadRepository.getSize(attachment.getSizeId()));
        return attachmentRequest;
    }
    private OrganizationParentRelationshipRequest getOrganizationParentRelationshipRequest(String id){
        OrganizationParentRelationship organizationParentRelationship = organizationDaoReadRepository.getOrganizationParentRelationship(id);
        OrganizationParentRelationshipRequest organizationParentRelationshipRequest = new OrganizationParentRelationshipRequest();
        BeanUtils.copyProperties(organizationParentRelationship, organizationParentRelationshipRequest);
        organizationParentRelationshipRequest.setOrganization(organizationDaoReadRepository.getOrganizationRef(String.valueOf(id)));
        return organizationParentRelationshipRequest;
    }

    private List<OrganizationChildRelationshipRequest> getOrganizationChildRelationshipRequest(String id){
        List<OrganizationChildRelationship> organizationChildRelationshipList = organizationDaoReadRepository.getOrganizationChildRelationship(id);
        List<OrganizationChildRelationshipRequest> organizationChildRelationshipRequestList = new ArrayList<>();
        for(OrganizationChildRelationship organizationChildRelationship: organizationChildRelationshipList){
            OrganizationChildRelationshipRequest organizationChildRelationshipRequest = new OrganizationChildRelationshipRequest();
            BeanUtils.copyProperties(organizationChildRelationship, organizationChildRelationshipRequest);
            organizationChildRelationshipRequest.setOrganization(organizationDaoReadRepository.getOrganizationRef(String.valueOf(organizationChildRelationship.getOrganizationRefId())));
            organizationChildRelationshipRequestList.add(organizationChildRelationshipRequest);
        }
        return organizationChildRelationshipRequestList;
    }

    private void deleteExistsDuring(String existsDuringId){
        individualDaoWriteRepository.deleteValidForById(existsDuringId);
    }
    private void deleteContactMediumByOrganizationId(String organizationId){
        List<ContactMedium> contactMediumList = individualDaoReadRepository.getContactMediumForOrganization(organizationId);
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
            individualDaoWriteRepository.deleteContactMediumByOrganizationId(organizationId);
        }
    }

    private void deleteCreditRating(String organizationId){
        List<CreditRating> creditRatingList = individualDaoReadRepository.getCreditRatingForOrganization(organizationId);
        if(creditRatingList!=null && creditRatingList.size()>0){
            List<String> validForIdListForCreditRating = creditRatingList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());
            if(validForIdListForCreditRating!=null && validForIdListForCreditRating.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForCreditRating);
            }
            individualDaoWriteRepository.deleteCreditRatingByOrganizationId(organizationId);
        }
    }
    private void deleteChildRelationship(String organizationId){
        organizationDaoWriteRepository.deleteChildRelationshipByOrganizationId(organizationId);
    }
    private void deleteExternalReference(String organizationId){
        individualDaoWriteRepository.deleteExternalReferenceByOrganizationId(organizationId);
    }
    private void deleteOrganizationIdentification(String organizationId){
        List<IndividualOrganizationIdentification> individualIdentificationList = organizationDaoReadRepository.getOrganizationIdentification(organizationId);
        if(individualIdentificationList!=null && individualIdentificationList.size()>0){
            List<String> validForIdListForIndividualIdentification = individualIdentificationList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());
            if(validForIdListForIndividualIdentification!=null && validForIdListForIndividualIdentification.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForIndividualIdentification);
            }
            individualDaoWriteRepository.deleteIndividualIdentificationByOrganizationId(organizationId);
        }
    }

    private void deleteOtherNameOrganization(String organizationId){
        List<OtherNameOrganization> otherNameList = individualDaoReadRepository.getOtherNameForOrganization(organizationId);
        if(otherNameList!=null && otherNameList.size()>0){
            List<String> validForIdListForOtherName = otherNameList
                    .stream()
                    .map(contactMedium -> String.valueOf(contactMedium.getValidForId()))
                    .collect(Collectors.toList());
            if(validForIdListForOtherName!=null && validForIdListForOtherName.size()>0){
                individualDaoWriteRepository.deleteValidForByIdList(validForIdListForOtherName);
            }
            individualDaoWriteRepository.deleteOtherNameByOrganizationId(organizationId);
        }
    }
    private void deletePartyCharacteristic(String organizationId){
        individualDaoWriteRepository.deletePartyCharacteristicByOrganizationId(organizationId);
    }

    private void deleteRelatedParty(String organizationId){
        individualDaoWriteRepository.deleteRelatedPartyByOrganizationId(organizationId);
    }
    private void deleteTaxExemptionCertificate(String organizationId){
        List<TaxExemptionCertificate> taxExemptionCertificateList = individualDaoReadRepository.getTaxExemptionCertificateForOrganization(organizationId);
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

            individualDaoWriteRepository.deleteTaxExemptionCertificateByOrganizationId(organizationId);
            if(attachmentIdListForTaxExemptionCertificate.size()>0){
                individualDaoWriteRepository.deleteAttachmentByIdList(attachmentIdListForTaxExemptionCertificate);
            }
        }
    }
    private void deleteParentRelationship( String parentRelationshipId){
        organizationDaoWriteRepository.deleteParentRelationshipByOrganizationId(parentRelationshipId);

    }
    @Override
    public String deleteOrganization(String organizationId) throws Exception{
        Organization organization = organizationDaoReadRepository.getOrganization(organizationId);
        //delete exists during
        deleteExistsDuring(organization.getExistsDuringId());

        //delete contact medium
        deleteContactMediumByOrganizationId(organizationId);

        //delete credit rating
        deleteCreditRating(organizationId);

        //delete child relationship
        deleteChildRelationship(organizationId);

        //DELETE EXTERNAL REFERENCE
        deleteExternalReference(organizationId);

        //delete Organization Identification
        deleteOrganizationIdentification(organizationId);

        //delete other name
        deleteOtherNameOrganization(organizationId);

        //DELETE party characteristic
        deletePartyCharacteristic(organizationId);

        //delete related party
        deleteRelatedParty(organizationId);

        //delete TaxExemptionCertificate
        deleteTaxExemptionCertificate(organizationId);

        Boolean result = organizationDaoWriteRepository.deleteOrganizationById(organizationId);

        //delete parent relationship
        deleteParentRelationship(organization.getOrganizationParentRelationshipId());

        if(result){
            return "Deleted";
        }else{
            throw new NotFoundException("Organization is not found with this id!");
        }
    }

    @Override
    public OrganizationResponse updateOrganization(String organizationId, OrganizationRequest organizationRequest) throws Exception {
        Organization organization = organizationDaoReadRepository.getOrganization(organizationId);

        if(organization==null){
            throw new NotFoundException("Organization is not found with this id!");
        }
        Map<String, String> map = new HashMap<>();
        map.put("is_head_office", "'"+organizationRequest.isHeadOffice()+"'");
        map.put("is_legal_entity", "'"+organizationRequest.isLegalEntity()+"'");

        if(StringUtils.hasLength(organizationRequest.getName())){
            map.put("name", "'"+organizationRequest.getName()+"'");
        }
        if(StringUtils.hasLength(organizationRequest.getNameType())){
            map.put("name_type", "'"+organizationRequest.getNameType()+"'");
        }
        if(StringUtils.hasLength(organizationRequest.getOrganizationType())){
            map.put("organization_type", "'"+organizationRequest.getOrganizationType()+"'");
        }
        if(StringUtils.hasLength(organizationRequest.getTradingName())){
            map.put("trading_name", "'"+organizationRequest.getTradingName()+"'");
        }
        if(StringUtils.hasLength(organizationRequest.getStatus())){
            map.put("status", "'"+organizationRequest.getStatus()+"'");
        }
        if(organizationRequest.getOrganizationParentRelationship()!=null){
            String newId = saveOrganizationParentRelationshipRequest(organizationRequest.getOrganizationParentRelationship());
            map.put("organization_parent_relationship_id", "'"+newId+"'");
        }
        organizationDaoWriteRepository.updateOrganizationById(organizationId, map);

        if(organizationRequest.getOrganizationParentRelationship()!=null){
            this.deleteParentRelationship(organization.getOrganizationParentRelationshipId());
        }
        if(organizationRequest.getExistsDuring()!=null){
            this.deleteExistsDuring(organization.getExistsDuringId());
            //save the new one
            Long existsDuringId = organizationDaoWriteRepository.saveValidFor(organizationRequest.getExistsDuring());
            organization.setExistsDuringId(String.valueOf(existsDuringId));
            organizationRequest.getExistsDuring().setId(existsDuringId);
        }
        if(organizationRequest.getContactMedium()!=null && organizationRequest.getContactMedium().size()>0){
            LOGGER.info("Deleting contact medium...");
            this.deleteContactMediumByOrganizationId(organizationId);

            LOGGER.info("Saving contact medium...");
            individualService.saveContactMedium(organizationRequest.getContactMedium(), null, organizationId);
        }
        if(organizationRequest.getCreditRating()!=null && organizationRequest.getCreditRating().size()>0){
            this.deleteCreditRating(organizationId);

            LOGGER.info("Saving credit rating...");
            individualService.saveCreditRatings(organizationRequest.getCreditRating(), null, organizationId);
        }
        if(organizationRequest.getExternalReference()!=null && organizationRequest.getExternalReference().size()>0){
            this.deleteExternalReference(organizationId);

            LOGGER.info("Saving external references...");
            individualService.saveExternalReferences(organizationRequest.getExternalReference(), null, organizationId);
        }
        //child relationship
        if(organizationRequest.getOrganizationChildRelationship()!=null && organizationRequest.getOrganizationChildRelationship().size()>0){
            this.deleteChildRelationship(organizationId);

            saveOrganizationChildRelationshipRequest(organizationRequest.getOrganizationChildRelationship(), organizationId);
        }
        if(organizationRequest.getOrganizationIdentification()!=null && organizationRequest.getOrganizationIdentification().size()>0){
            this.deleteOrganizationIdentification(organizationId);

            LOGGER.info("Saving individual identifications...");
            individualService.saveIndividualOrganizationIdentifications(organizationRequest.getOrganizationIdentification(), null, organizationId);
        }
        if(organizationRequest.getOtherName()!=null && organizationRequest.getOtherName().size()>0){
            this.deleteOtherNameOrganization(organizationId);

            LOGGER.info("Saving other names...");
            saveOtherNames(organizationRequest.getOtherName(), organizationId);
        }
        if(organizationRequest.getPartyCharacteristic()!=null && organizationRequest.getPartyCharacteristic().size()>0){
            this.deletePartyCharacteristic(organizationId);

            LOGGER.info("Saving party characteristics...");
            individualService.savePartyCharacteristics(organizationRequest.getPartyCharacteristic(), null, organizationId);
        }
        if(organizationRequest.getRelatedParty()!=null && organizationRequest.getRelatedParty().size()>0){
            this.deleteRelatedParty(organizationId);

            LOGGER.info("Saving related parties...");
            individualService.saveRelatedParties(organizationRequest.getRelatedParty(), null, organizationId);
        }
        if(organizationRequest.getTaxExemptionCertificate()!=null && organizationRequest.getTaxExemptionCertificate().size()>0){
            this.deleteTaxExemptionCertificate(organizationId);

            LOGGER.info("Saving tax exemption certificates...");
            individualService.saveTaxExemptionCertificates(organizationRequest.getTaxExemptionCertificate(), null, organizationId);
        }
        OrganizationResponse organizationResponse = this.getOrganizationById(organizationId);
        return organizationResponse;
    }
}
