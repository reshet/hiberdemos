--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


SET search_path = ofm, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE offer_adjustment (
    id bigint NOT NULL
);

ALTER TABLE offer_adjustment OWNER TO "sars-core";

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO "sars-core";