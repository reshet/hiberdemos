--
-- PostgreSQL database dump
--


CREATE TABLE generator_provided_entity (
    id bigint NOT NULL
);


ALTER TABLE generator_provided_entity OWNER TO "sars-core";

CREATE SEQUENCE id_gen_seq
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE id_gen_seq OWNER TO "sars-core";