--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-06-13 14:05:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 65796)
-- Name: albums; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.albums (
    id bigint NOT NULL,
    artist character varying(255) NOT NULL,
    cover character varying(255) NOT NULL,
    release_date date NOT NULL,
    title character varying(255) NOT NULL
);


ALTER TABLE public.albums OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 65810)
-- Name: albums_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.albums_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.albums_seq OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 65803)
-- Name: tracks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tracks (
    id bigint NOT NULL,
    length character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    track_number integer NOT NULL,
    album_id bigint
);


ALTER TABLE public.tracks OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 65811)
-- Name: tracks_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tracks_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tracks_seq OWNER TO postgres;

--
-- TOC entry 3331 (class 0 OID 65796)
-- Dependencies: 214
-- Data for Name: albums; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.albums (id, artist, cover, release_date, title) FROM stdin;
\.


--
-- TOC entry 3332 (class 0 OID 65803)
-- Dependencies: 215
-- Data for Name: tracks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tracks (id, length, title, track_number, album_id) FROM stdin;
\.


--
-- TOC entry 3340 (class 0 OID 0)
-- Dependencies: 216
-- Name: albums_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.albums_seq', 1, false);


--
-- TOC entry 3341 (class 0 OID 0)
-- Dependencies: 217
-- Name: tracks_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tracks_seq', 1, false);


--
-- TOC entry 3179 (class 2606 OID 65818)
-- Name: albums albums_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_id_key UNIQUE (id);


--
-- TOC entry 3181 (class 2606 OID 65802)
-- Name: albums albums_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_pkey PRIMARY KEY (id);


--
-- TOC entry 3183 (class 2606 OID 65830)
-- Name: albums albums_title_artist_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_title_artist_key UNIQUE (title, artist);


--
-- TOC entry 3185 (class 2606 OID 65820)
-- Name: tracks tracks_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_id_key UNIQUE (id);


--
-- TOC entry 3187 (class 2606 OID 65809)
-- Name: tracks tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_pkey PRIMARY KEY (id);


--
-- TOC entry 3188 (class 2606 OID 65812)
-- Name: tracks fkdcmijveo7n1lql01vav1u2jd2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT fkdcmijveo7n1lql01vav1u2jd2 FOREIGN KEY (album_id) REFERENCES public.albums(id);


-- Completed on 2023-06-13 14:05:09

--
-- PostgreSQL database dump complete
--

