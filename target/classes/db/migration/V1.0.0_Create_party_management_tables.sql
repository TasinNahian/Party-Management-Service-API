CREATE TABLE IF NOT EXISTS public.valid_for_pm
(
    id bigint NOT NULL PRIMARY KEY,
    start_datetime timestamp with time zone NULL,
    end_datetime timestamp with time zone NULL
);

CREATE TABLE IF NOT EXISTS public.size_pm
(
    id bigint NOT NULL PRIMARY KEY,
    units character varying(250),
    amount double precision NULL
);

CREATE TABLE IF NOT EXISTS public.individual
(
      id character varying(250) NOT NULL PRIMARY KEY,
      href character varying(250),
      aristocratic_title  character varying(250),
      birth_date timestamp with time zone,
      country_of_birth character varying(250),
      death_date timestamp with time zone,
      family_name character varying(250) NOT NULL,
      family_name_prefix character varying(250),
      formatted_name character varying(250),
      full_name character varying(250),
      gender character varying(250),
      generation character varying(250),
      given_name character varying(250) NOT NULL,
      legal_name character varying(250),
      location character varying(250),
      marital_status character varying(250),
      middle_name character varying(250),
      nationality character varying(250),
      place_of_birth character varying(250),
      preferred_given_name character varying(250),
      title character varying(250),
      status character varying(250),
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250)
);

--only organization
CREATE TABLE IF NOT EXISTS public.organization_ref
(
      id character varying(250) NOT NULL PRIMARY KEY,
      href character varying(250),
      name character varying(250),
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250),
      referred_type character varying(250)
);

--only organization
CREATE TABLE IF NOT EXISTS public.organization_parent_relationship
(
    id character varying(250) NOT NULL PRIMARY KEY,
    relationship_type character varying(250),
    base_type character varying(250),
    schema_location character varying(250),
    type character varying(250),
    organization_ref_id character varying(250),
    FOREIGN KEY (organization_ref_id) REFERENCES organization_ref (id)
);

--organization
CREATE TABLE IF NOT EXISTS public.organization
  (
       id character varying(250) NOT NULL PRIMARY KEY,
       is_head_office boolean,
       is_legal_entity boolean,
       name character varying(250) ,
       name_type character varying(250) ,
       organization_type character varying(250) ,
       trading_name character varying(250) NOT NULL,
       status character varying(250),
       base_type character varying(250) ,
       schema_location character varying(250) ,
       type character varying(250),
       organization_parent_relationship_id character varying(250),
       exists_during_id character varying(250),
       FOREIGN KEY (organization_parent_relationship_id) REFERENCES organization_parent_relationship (id)
  );

--both
--creditRating
CREATE TABLE IF NOT EXISTS public.party_credit_profile
(
     id character varying(250) NOT NULL PRIMARY KEY,
     credit_agency_name character varying(250),
     credit_agency_type character varying(250),
     rating_reference character varying(250),
     rating_score integer,
     valid_for_id bigint,
     base_type character varying(250),
     schema_location character varying(250),
     type character varying(250),
     organization_id character varying(250),
     individual_id character varying(250),
     FOREIGN KEY (individual_id) REFERENCES individual (id),
     FOREIGN KEY (organization_id) REFERENCES organization (id)
);

CREATE TABLE IF NOT EXISTS public.disability
(
     id character varying(250) NOT NULL PRIMARY KEY,
     disability_code character varying(250),
     disability_name character varying(250),
     valid_for_id bigint,
     base_type character varying(250),
     schema_location character varying(250),
     type character varying(250),
     individual_id character varying(250),
     FOREIGN KEY (individual_id) REFERENCES individual (id)
);

--both
CREATE TABLE IF NOT EXISTS public.external_reference
(
    id character varying(250) NOT NULL PRIMARY KEY,
    external_reference_type character varying(250),
    name character varying(250),
    base_type character varying(250),
    schema_location character varying(250),
    type character varying(250),
    individual_id character varying(250),
    organization_id character varying(250),
    FOREIGN KEY (individual_id) REFERENCES individual (id),
    FOREIGN KEY (organization_id) REFERENCES organization (id)
);

CREATE TABLE IF NOT EXISTS public.other_name_individual
(
  id character varying(250) NOT NULL PRIMARY KEY,
  aristocratic_title character varying(250),
  family_name character varying(250),
  family_name_prefix character varying(250),
  formatted_name character varying(250),
  full_name character varying(250),
  generation character varying(250),
  given_name character varying(250),
  legal_name character varying(250),
  middle_name character varying(250),
  preferred_given_name character varying(250),
  title character varying(250),
  valid_for_id bigint,
  base_type character varying(250),
  schema_location character varying(250),
  type character varying(250),
  individual_id character varying(250),
  FOREIGN KEY (individual_id) REFERENCES individual (id)
);

--both
--partyCharacteristic
CREATE TABLE IF NOT EXISTS public.characteristic
(
    id character varying(250) NOT NULL PRIMARY KEY,
    name character varying(250) NOT NULL,
    value_type character varying(250),
    value character varying(250) NOT NULL,
    base_type character varying(250),
    schema_location character varying(250),
    type character varying(250),
    individual_id character varying(250),
    organization_id character varying(250),
    FOREIGN KEY (individual_id) REFERENCES individual (id),
    FOREIGN KEY (organization_id) REFERENCES organization (id)
);

CREATE TABLE IF NOT EXISTS public.language_ability
(
  id character varying(250) NOT NULL PRIMARY KEY,
  is_favourite_language boolean,
  language_code character varying(250),
  language_name character varying(250),
  listening_proficiency character varying(250),
  reading_proficiency character varying(250),
  speaking_proficiency character varying(250),
  writing_proficiency character varying(250),
  valid_for_id bigint,
  base_type character varying(250),
  schema_location character varying(250),
  type character varying(250),
  individual_id character varying(250),
  FOREIGN KEY (individual_id) REFERENCES individual (id)
);

--both
--attachment
CREATE TABLE IF NOT EXISTS public.attachment_ref
(
        id character varying(250) NOT NULL PRIMARY KEY,
        href character varying(250),
        attachment_type character varying(250),
        content character varying(250),
        description character varying(250),
        is_ref boolean,
        mime_type character varying(250),
        name character varying(250),
        url character varying(250),
        size_pm_id character varying(250),
        valid_for_id bigint,
        base_type character varying(250),
        schema_location character varying(250),
        type character varying(250),
        referred_type character varying(250)
);

CREATE TABLE IF NOT EXISTS public.individual_organization_identification
(
      identification_id character varying(250) NOT NULL PRIMARY KEY,
      identification_type character varying(250),
      issuing_authority character varying(250),
      issuing_date timestamp with time zone,
      valid_for_id bigint,
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250),
      attachment_ref_id character varying(250),
      organization_id character varying(250),
      individual_id character varying(250),
      FOREIGN KEY (individual_id) REFERENCES individual (id),
      FOREIGN KEY (organization_id) REFERENCES organization (id),
      FOREIGN KEY (attachment_ref_id) REFERENCES attachment_ref (id)
);

--both
CREATE TABLE IF NOT EXISTS public.tax_exemption_certificate
(
    id character varying(250) NOT NULL PRIMARY KEY,
    valid_for_id bigint,
    base_type character varying(250),
    schema_location character varying(250),
    type character varying(250),
    attachment_ref_id character varying(250),
    individual_id character varying(250),
    organization_id character varying(250),
    FOREIGN KEY (individual_id) REFERENCES individual (id),
    FOREIGN KEY (attachment_ref_id) REFERENCES attachment_ref (id),
    FOREIGN KEY (organization_id) REFERENCES organization (id)
);

--both
CREATE TABLE IF NOT EXISTS public.tax_definition
(
      id character varying(250) NOT NULL PRIMARY KEY,
      name character varying(250),
      tax_type character varying(250),
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250),
      referred_type character varying(250),
      tax_exemption_certificate_id character varying(250),
      FOREIGN KEY (tax_exemption_certificate_id) REFERENCES tax_exemption_certificate (id)
);

--both
CREATE TABLE IF NOT EXISTS public.medium_characteristic
(
        id character varying(250) NOT NULL PRIMARY KEY,
        city character varying(250),
        contact_type character varying(250),
        country character varying(250),
        email_address character varying(250),
        fax_number character varying(250),
        phone_number character varying(250),
        post_code character varying(250),
        social_network_id character varying(250),
        state_or_province character varying(250),
        street1 character varying(250),
        street2 character varying(250),
        base_type character varying(250),
        schema_location character varying(250),
        type character varying(250)
);

--both
CREATE TABLE IF NOT EXISTS public.contact_medium
(
      id character varying(250) NOT NULL PRIMARY KEY,
      medium_type character varying(250),
      preferred boolean,
      valid_for_id bigint,
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250),
      organization_id character varying(250),
      medium_characteristic_id character varying(250),
      individual_id character varying(250),
      FOREIGN KEY (individual_id) REFERENCES individual (id),
      FOREIGN KEY (medium_characteristic_id) REFERENCES medium_characteristic (id),
      FOREIGN KEY (organization_id) REFERENCES organization(id)
);

--both
CREATE TABLE IF NOT EXISTS public.related_party
(
      id character varying(250) NOT NULL,
      href character varying(250),
      name character varying(250),
      role character varying(250),
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250),
      referred_type character varying(250) NOT NULL,
      individual_id character varying(250),
      organization_id character varying(250),
      FOREIGN KEY (individual_id) REFERENCES individual (id),
      FOREIGN KEY (organization_id) REFERENCES organization (id)
);


CREATE TABLE IF NOT EXISTS public.skill
(
      id character varying(250) NOT NULL PRIMARY KEY,
      comment character varying(250),
      evaluated_level character varying(250),
      skill_code character varying(250),
      skill_name character varying(250),
      valid_for_id bigint,
      base_type character varying(250),
      schema_location character varying(250),
      type character varying(250),
      individual_id character varying(250),
      FOREIGN KEY (individual_id) REFERENCES individual (id)
);

--only for the organization
CREATE TABLE IF NOT EXISTS public.other_name_organization
(
      id character varying(250) NOT NULL PRIMARY KEY,
      name character varying(250) ,
      name_type character varying(250) ,
      trading_name character varying(250) ,
      valid_for_id bigint,
      base_type character varying(250) ,
      schema_location character varying(250) ,
      type character varying(250),
      organization_id character varying(250),
      FOREIGN KEY (organization_id) REFERENCES organization (id)
);

--only for the organization
CREATE TABLE IF NOT EXISTS public.organization_child_relationship
(
    id character varying(250) NOT NULL PRIMARY KEY,
    relationship_type character varying(250),
    base_type character varying(250),
    schema_location character varying(250),
    type character varying(250),
    organization_id character varying(250),
    organization_ref_id character varying(250),
    FOREIGN KEY (organization_ref_id) REFERENCES organization_ref (id),
    FOREIGN KEY (organization_id) REFERENCES organization(id)
);


