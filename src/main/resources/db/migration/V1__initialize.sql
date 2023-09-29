--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-09-29 13:09:38

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

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS '';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 98334)
-- Name: albums; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.albums (
    id bigint NOT NULL,
    cover character varying(255) NOT NULL,
    release_date date NOT NULL,
    title character varying(255) NOT NULL,
    artist_id bigint NOT NULL,
    rating integer
);


ALTER TABLE public.albums OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 98442)
-- Name: albums_genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.albums_genres (
    album_id bigint NOT NULL,
    genre_id bigint NOT NULL
);


ALTER TABLE public.albums_genres OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 98339)
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
-- TOC entry 217 (class 1259 OID 98340)
-- Name: artists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artists (
    id bigint NOT NULL,
    title character varying(255) NOT NULL
);


ALTER TABLE public.artists OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 98345)
-- Name: artists_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.artists_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.artists_seq OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 98377)
-- Name: genres; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.genres (
    id bigint NOT NULL,
    title character varying(255) NOT NULL
);


ALTER TABLE public.genres OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 98346)
-- Name: tracks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tracks (
    id bigint NOT NULL,
    title character varying(255) NOT NULL,
    track_number integer NOT NULL,
    album_id bigint NOT NULL,
    seconds integer NOT NULL
);


ALTER TABLE public.tracks OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 98349)
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
-- TOC entry 3218 (class 2606 OID 98446)
-- Name: albums_genres albums_genres_album_id_genre_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums_genres
    ADD CONSTRAINT albums_genres_album_id_genre_id_key UNIQUE (album_id, genre_id);


--
-- TOC entry 3200 (class 2606 OID 98351)
-- Name: albums albums_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_id_key UNIQUE (id);


--
-- TOC entry 3202 (class 2606 OID 98353)
-- Name: albums albums_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_pkey PRIMARY KEY (id);


--
-- TOC entry 3204 (class 2606 OID 98415)
-- Name: albums albums_title_artist_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_title_artist_key UNIQUE (title, artist_id);


--
-- TOC entry 3206 (class 2606 OID 98357)
-- Name: artists artists_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_id_key UNIQUE (id);


--
-- TOC entry 3208 (class 2606 OID 98359)
-- Name: artists artists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (id);


--
-- TOC entry 3210 (class 2606 OID 98425)
-- Name: artists artists_title_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_title_key UNIQUE (title);


--
-- TOC entry 3216 (class 2606 OID 98381)
-- Name: genres genres_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.genres
    ADD CONSTRAINT genres_pkey PRIMARY KEY (id);


--
-- TOC entry 3212 (class 2606 OID 98363)
-- Name: tracks tracks_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_id_key UNIQUE (id);


--
-- TOC entry 3214 (class 2606 OID 98365)
-- Name: tracks tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_pkey PRIMARY KEY (id);


--
-- TOC entry 3219 (class 2606 OID 98366)
-- Name: albums albums_artist_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_artist_id_fkey FOREIGN KEY (artist_id) REFERENCES public.artists(id);


--
-- TOC entry 3221 (class 2606 OID 98452)
-- Name: albums_genres albums_genres_album_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums_genres
    ADD CONSTRAINT albums_genres_album_id_fkey FOREIGN KEY (album_id) REFERENCES public.albums(id);


--
-- TOC entry 3222 (class 2606 OID 98447)
-- Name: albums_genres albums_genres_genre_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums_genres
    ADD CONSTRAINT albums_genres_genre_id_fkey FOREIGN KEY (genre_id) REFERENCES public.genres(id);


--
-- TOC entry 3220 (class 2606 OID 98371)
-- Name: tracks tracks_album_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_album_id_fkey FOREIGN KEY (album_id) REFERENCES public.albums(id);


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2023-09-29 13:09:38

--
-- PostgreSQL database dump complete
--

