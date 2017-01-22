--
-- PostgreSQL database dump
--

CREATE TABLE entity_with_string_array (
    id bigint NOT NULL,
    string_array CHARACTER VARYING (1000) NOT NULL
);

ALTER TABLE entity_with_string_array OWNER TO "sars-core";