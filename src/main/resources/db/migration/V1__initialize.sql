--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-06-22 15:47:59

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
-- TOC entry 5 (class 2615 OID 73748)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS '';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 215 (class 1259 OID 73758)
-- Name: albums; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.albums (
    id bigint NOT NULL,
    cover character varying(255) NOT NULL,
    release_date date NOT NULL,
    title character varying(255) NOT NULL,
    artist_id bigint NOT NULL
);


ALTER TABLE public.albums OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 73763)
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
-- TOC entry 219 (class 1259 OID 73785)
-- Name: artists; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.artists (
    id bigint NOT NULL,
    title character varying NOT NULL
);


ALTER TABLE public.artists OWNER TO postgres;



--
-- TOC entry 217 (class 1259 OID 73764)
-- Name: tracks; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tracks (
    id bigint NOT NULL,
    length character varying(255) NOT NULL,
    title character varying(255) NOT NULL,
    track_number integer NOT NULL,
    album_id bigint NOT NULL
);


ALTER TABLE public.tracks OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 73769)
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
-- TOC entry 3349 (class 0 OID 73758)
-- Dependencies: 215
-- Data for Name: albums; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.albums (id, cover, release_date, title, artist_id) FROM stdin;
302	https://m.media-amazon.com/images/I/71m0ofUWYXL._SX522_.jpg	2023-06-07	sddsds	1
52	https://m.media-amazon.com/images/I/71m0ofUWYXL._SX522_.jpg	2023-06-16	dsdsds	1
152	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsds	1
252	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-16	Our Endless Numbered Days	1
202	https://m.media-amazon.com/images/I/71m0ofUWYXL._SX522_.jpg	2023-06-16	22222	1
552	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdsds	1
802	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdffdfdsdsddsds	1
852	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdf2fdfdsdsddsds	1
853	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdf2fdfdsd22sddsds	1
854	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdf2fdfdsd22sddssds	1
602	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsd	1
1002	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdf2fdfdsd22sddssds	1
1003	https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg	2023-06-15	dsdsdsdf2fdfdsd22sddssds	1
\.


--
-- TOC entry 3353 (class 0 OID 73785)
-- Dependencies: 219
-- Data for Name: artists; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.artists (id, title) FROM stdin;
1	Tom Waits
\.



--
-- TOC entry 3351 (class 0 OID 73764)
-- Dependencies: 217
-- Data for Name: tracks; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tracks (id, length, title, track_number, album_id) FROM stdin;
302	1:1	aaabbbbbccc	5	52
352	1:1	aaabbbbbccc	5	52
553	0:00	222332111	2	252
552	11:00	On Your Wings	1	252
503	0:00	sdds	2	202
502	0:00	222	1	202
602	2:00	2	3	202
\.


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 216
-- Name: albums_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.albums_seq', 1101, true);


--
-- TOC entry 3362 (class 0 OID 0)
-- Dependencies: 218
-- Name: tracks_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tracks_seq', 851, true);


--
-- TOC entry 3191 (class 2606 OID 73771)
-- Name: albums albums_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_id_key UNIQUE (id);


--
-- TOC entry 3193 (class 2606 OID 73773)
-- Name: albums albums_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_pkey PRIMARY KEY (id);


--
-- TOC entry 3199 (class 2606 OID 73804)
-- Name: artists artists_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_id_key UNIQUE (id);


--
-- TOC entry 3201 (class 2606 OID 73793)
-- Name: artists artists_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_pkey PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 73802)
-- Name: artists artists_title_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.artists
    ADD CONSTRAINT artists_title_key UNIQUE (title);


--
-- TOC entry 3195 (class 2606 OID 73777)
-- Name: tracks tracks_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_id_key UNIQUE (id);


--
-- TOC entry 3197 (class 2606 OID 73779)
-- Name: tracks tracks_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT tracks_pkey PRIMARY KEY (id);



--
-- TOC entry 3204 (class 2606 OID 73796)
-- Name: albums albums_artist_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.albums
    ADD CONSTRAINT albums_artist_id_fkey FOREIGN KEY (artist_id) REFERENCES public.artists(id);


--
-- TOC entry 3205 (class 2606 OID 73780)
-- Name: tracks fkdcmijveo7n1lql01vav1u2jd2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tracks
    ADD CONSTRAINT fkdcmijveo7n1lql01vav1u2jd2 FOREIGN KEY (album_id) REFERENCES public.albums(id);


--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE USAGE ON SCHEMA public FROM PUBLIC;


-- Completed on 2023-06-22 15:47:59

--
-- PostgreSQL database dump complete
--

