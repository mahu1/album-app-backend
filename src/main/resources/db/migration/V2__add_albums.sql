--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2023-11-21 13:41:17

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
-- TOC entry 3369 (class 0 OID 114796)
-- Dependencies: 218
-- Data for Name: artists; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.artists VALUES (703, 'Frank Zappa');
INSERT INTO public.artists VALUES (704, 'Pink Floyd');
INSERT INTO public.artists VALUES (705, 'Iron & Wine');
INSERT INTO public.artists VALUES (706, 'King Crimson');
INSERT INTO public.artists VALUES (707, 'Nick Drake');
INSERT INTO public.artists VALUES (709, 'The Allman Brothers Band');
INSERT INTO public.artists VALUES (702, 'Santana');
INSERT INTO public.artists VALUES (708, 'Bob Dylan');


--
-- TOC entry 3366 (class 0 OID 114787)
-- Dependencies: 215
-- Data for Name: albums; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.albums VALUES (1702, 'https://m.media-amazon.com/images/I/91BKYvMis1L._AC_SX679_.jpg', '1969-08-22', 'Santana', 702, NULL);
INSERT INTO public.albums VALUES (1708, 'https://m.media-amazon.com/images/I/81IV6-2AkRL._AC_SX679_.jpg', '1971-10-30', 'Meddle', 704, NULL);
INSERT INTO public.albums VALUES (1709, 'https://m.media-amazon.com/images/I/51XdzcVR2bL._AC_.jpg', '2002-09-24', 'The Creek Drank the Cradle', 705, NULL);
INSERT INTO public.albums VALUES (1714, 'https://m.media-amazon.com/images/I/71TOz72D0JL._AC_SX679_.jpg', '1972-02-25', 'Pink Moon', 707, NULL);
INSERT INTO public.albums VALUES (1703, 'https://m.media-amazon.com/images/I/91pM5XNmlPL._AC_SX679_.jpg', '1970-09-23', 'Abraxas', 702, NULL);
INSERT INTO public.albums VALUES (1707, 'https://m.media-amazon.com/images/I/71m0ofUWYXL._AC_SX679_.jpg', '1975-09-12', 'Wish You Were Here', 704, NULL);
INSERT INTO public.albums VALUES (1718, 'https://m.media-amazon.com/images/I/81TiDFSXXAL._AC_SX679_.jpg', '1971-07-06', 'At Fillmore East', 709, NULL);
INSERT INTO public.albums VALUES (1716, 'https://m.media-amazon.com/images/I/71T1UAd9XFL._AC_SX679_.jpg', '1960-11-04', 'The Allman Brothers Band', 709, NULL);
INSERT INTO public.albums VALUES (1717, 'https://m.media-amazon.com/images/I/51ZKLWeDX0L._AC_.jpg', '1970-09-23', 'Idlewild South', 709, NULL);
INSERT INTO public.albums VALUES (1706, 'https://m.media-amazon.com/images/I/51npf8BoRwL._UX716_FMwebp_QL85_.jpg', '1977-01-21', 'Animals', 704, NULL);
INSERT INTO public.albums VALUES (1713, 'https://m.media-amazon.com/images/I/51S0rNLzOQL._AC_.jpg', '1971-03-05', 'Bryter Layter', 707, NULL);
INSERT INTO public.albums VALUES (1719, 'https://m.media-amazon.com/images/I/71bF-Z3JNCL._AC_SX679_.jpg', '1972-02-12', 'Eat a Peach', 709, NULL);
INSERT INTO public.albums VALUES (1715, 'https://m.media-amazon.com/images/I/81x8ZJqMm7L._AC_SX679_.jpg', '1975-01-20', 'Blood on the Tracks', 708, NULL);
INSERT INTO public.albums VALUES (1720, 'https://m.media-amazon.com/images/I/61z6Esv-syL._AC_.jpg', '1973-08-01', 'Brothers and Sisters', 709, NULL);
INSERT INTO public.albums VALUES (1705, 'https://m.media-amazon.com/images/I/71L9Wv9K1rL._AC_SX679_.jpg', '1969-10-10', 'Hot Rats', 703, NULL);
INSERT INTO public.albums VALUES (1711, 'https://m.media-amazon.com/images/I/71FM257lYjL._AC_SX679_.jpg', '1969-10-10', 'In the Court of the Crimson King', 706, NULL);
INSERT INTO public.albums VALUES (1712, 'https://m.media-amazon.com/images/I/41DmDi4tLAL._AC_.jpg', '1969-07-03', 'Five Leaves Left', 707, NULL);
INSERT INTO public.albums VALUES (1704, 'https://m.media-amazon.com/images/I/81WIboLOZ1L._AC_SX679_.jpg', '1971-09-01', '3', 702, NULL);
INSERT INTO public.albums VALUES (1710, 'https://m.media-amazon.com/images/I/61Xzf+RI9sL._AC_.jpg', '2004-03-23', 'Our Endless Numbered Days', 705, NULL);


--
-- TOC entry 3371 (class 0 OID 114800)
-- Dependencies: 220
-- Data for Name: genres; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.genres VALUES (1, 'rock');
INSERT INTO public.genres VALUES (2, 'pop');
INSERT INTO public.genres VALUES (3, 'jazz');
INSERT INTO public.genres VALUES (5, 'country');
INSERT INTO public.genres VALUES (6, 'classical');
INSERT INTO public.genres VALUES (7, 'folk');
INSERT INTO public.genres VALUES (4, 'blues');


--
-- TOC entry 3367 (class 0 OID 114792)
-- Dependencies: 216
-- Data for Name: albums_genres; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.albums_genres VALUES (1708, 1);
INSERT INTO public.albums_genres VALUES (1719, 1);
INSERT INTO public.albums_genres VALUES (1719, 4);
INSERT INTO public.albums_genres VALUES (1714, 7);
INSERT INTO public.albums_genres VALUES (1720, 1);
INSERT INTO public.albums_genres VALUES (1720, 4);
INSERT INTO public.albums_genres VALUES (1707, 1);
INSERT INTO public.albums_genres VALUES (1706, 1);
INSERT INTO public.albums_genres VALUES (1709, 7);
INSERT INTO public.albums_genres VALUES (1716, 4);
INSERT INTO public.albums_genres VALUES (1716, 1);
INSERT INTO public.albums_genres VALUES (1703, 1);
INSERT INTO public.albums_genres VALUES (1717, 1);
INSERT INTO public.albums_genres VALUES (1717, 4);
INSERT INTO public.albums_genres VALUES (1715, 7);
INSERT INTO public.albums_genres VALUES (1712, 7);
INSERT INTO public.albums_genres VALUES (1704, 1);
INSERT INTO public.albums_genres VALUES (1710, 7);
INSERT INTO public.albums_genres VALUES (1705, 3);
INSERT INTO public.albums_genres VALUES (1705, 1);
INSERT INTO public.albums_genres VALUES (1702, 1);
INSERT INTO public.albums_genres VALUES (1718, 1);
INSERT INTO public.albums_genres VALUES (1718, 4);
INSERT INTO public.albums_genres VALUES (1713, 7);
INSERT INTO public.albums_genres VALUES (1711, 1);



--
-- TOC entry 3372 (class 0 OID 114803)
-- Dependencies: 221
-- Data for Name: tracks; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.tracks VALUES (1482, 'Little Umbrellas', 4, 1705, 186);
INSERT INTO public.tracks VALUES (1485, 'Pigs on the Wing (Part One)', 1, 1706, 84);
INSERT INTO public.tracks VALUES (1486, 'Dogs', 2, 1706, 1024);
INSERT INTO public.tracks VALUES (1487, 'Pigs (Three Different Ones)', 3, 1706, 688);
INSERT INTO public.tracks VALUES (1488, 'Sheep', 4, 1706, 620);
INSERT INTO public.tracks VALUES (1489, 'Pigs on the Wing (Part Two)', 5, 1706, 84);
INSERT INTO public.tracks VALUES (1490, 'Shine On You Crazy Diamond (Parts I–V)', 1, 1707, 812);
INSERT INTO public.tracks VALUES (1491, 'Welcome to the Machine', 2, 1707, 448);
INSERT INTO public.tracks VALUES (1492, 'Have a Cigar', 3, 1707, 308);
INSERT INTO public.tracks VALUES (1493, 'Wish You Were Here', 4, 1707, 335);
INSERT INTO public.tracks VALUES (1495, 'One of These Days', 1, 1708, 357);
INSERT INTO public.tracks VALUES (1496, 'A Pillow of Winds', 2, 1708, 313);
INSERT INTO public.tracks VALUES (1497, 'Fearless', 3, 1708, 368);
INSERT INTO public.tracks VALUES (1498, 'San Tropez', 4, 1708, 224);
INSERT INTO public.tracks VALUES (1499, 'Seamus', 5, 1708, 135);
INSERT INTO public.tracks VALUES (1500, 'Echoes', 6, 1708, 1410);
INSERT INTO public.tracks VALUES (1501, 'Lion''s Mane', 1, 1709, 169);
INSERT INTO public.tracks VALUES (1502, 'Bird Stealing Bread', 2, 1709, 261);
INSERT INTO public.tracks VALUES (1503, 'Faded from the Winter', 3, 1709, 197);
INSERT INTO public.tracks VALUES (1504, 'Promising Light', 4, 1709, 169);
INSERT INTO public.tracks VALUES (1505, 'The Rooster Moans', 5, 1709, 204);
INSERT INTO public.tracks VALUES (1506, 'Upward Over the Mountain', 6, 1709, 356);
INSERT INTO public.tracks VALUES (1507, 'Southern Anthem', 7, 1709, 234);
INSERT INTO public.tracks VALUES (1508, 'An Angry Blade', 8, 1709, 228);
INSERT INTO public.tracks VALUES (1509, 'Weary Memory', 9, 1709, 241);
INSERT INTO public.tracks VALUES (1510, 'Promise What You Will', 10, 1709, 144);
INSERT INTO public.tracks VALUES (1511, 'Muddy Hymnal', 11, 1709, 163);
INSERT INTO public.tracks VALUES (1512, 'On Your Wings', 1, 1710, 233);
INSERT INTO public.tracks VALUES (1513, 'Naked as We Came', 2, 1710, 153);
INSERT INTO public.tracks VALUES (1514, 'Cinder and Smoke', 3, 1710, 344);
INSERT INTO public.tracks VALUES (1515, 'Sunset Soon Forgotten', 4, 1710, 200);
INSERT INTO public.tracks VALUES (1516, 'Teeth in the Grass', 5, 1710, 142);
INSERT INTO public.tracks VALUES (1517, 'Love and Some Verses', 6, 1710, 220);
INSERT INTO public.tracks VALUES (1518, 'Radio War', 7, 1710, 116);
INSERT INTO public.tracks VALUES (1519, 'Each Coming Night', 8, 1710, 208);
INSERT INTO public.tracks VALUES (1520, 'Free Until They Cut Me Down', 9, 1710, 275);
INSERT INTO public.tracks VALUES (1521, 'Fever Dream', 10, 1710, 256);
INSERT INTO public.tracks VALUES (1522, 'Sodom, South Georgia', 11, 1710, 299);
INSERT INTO public.tracks VALUES (1523, 'Passing Afternoon', 12, 1710, 241);
INSERT INTO public.tracks VALUES (1524, '21st Century Schizoid Man (including "Mirrors")', 1, 1711, 444);
INSERT INTO public.tracks VALUES (1529, 'Time Has Told Me', 1, 1712, 267);
INSERT INTO public.tracks VALUES (1453, 'Evil Ways', 2, 1702, 234);
INSERT INTO public.tracks VALUES (1454, 'Shades of Time', 3, 1702, 194);
INSERT INTO public.tracks VALUES (1455, 'Savor', 4, 1702, 167);
INSERT INTO public.tracks VALUES (1456, 'Jingo', 5, 1702, 261);
INSERT INTO public.tracks VALUES (1457, 'Persuasion', 6, 1702, 153);
INSERT INTO public.tracks VALUES (1458, 'Treat', 7, 1702, 283);
INSERT INTO public.tracks VALUES (1459, 'You Just Don''t Care', 8, 1702, 274);
INSERT INTO public.tracks VALUES (1461, 'Singing Winds, Crying Beasts', 1, 1703, 291);
INSERT INTO public.tracks VALUES (1462, 'Black Magic Woman/Gypsy Queen', 2, 1703, 324);
INSERT INTO public.tracks VALUES (1464, 'Incident at Neshabur', 4, 1703, 298);
INSERT INTO public.tracks VALUES (1465, 'Se a Cabó', 5, 1703, 170);
INSERT INTO public.tracks VALUES (1466, 'Mother''s Daughter', 6, 1703, 265);
INSERT INTO public.tracks VALUES (1467, 'Samba Pa Ti', 7, 1703, 285);
INSERT INTO public.tracks VALUES (1468, 'Hope You''re Feeling Better', 8, 1703, 250);
INSERT INTO public.tracks VALUES (1469, 'El Nicoya', 9, 1703, 90);
INSERT INTO public.tracks VALUES (1472, 'Taboo', 3, 1704, 334);
INSERT INTO public.tracks VALUES (1473, 'Toussaint L''Overture', 4, 1704, 356);
INSERT INTO public.tracks VALUES (1474, 'Everybody''s Everything', 5, 1704, 211);
INSERT INTO public.tracks VALUES (1475, 'Guajira', 6, 1704, 343);
INSERT INTO public.tracks VALUES (1476, 'Jungle Strut', 7, 1704, 320);
INSERT INTO public.tracks VALUES (1480, 'Willie the Pimp', 2, 1705, 561);
INSERT INTO public.tracks VALUES (1481, 'Son of Mr. Green Genes', 3, 1705, 538);
INSERT INTO public.tracks VALUES (1530, 'River Man', 2, 1712, 261);
INSERT INTO public.tracks VALUES (1531, 'Three Hours', 3, 1712, 376);
INSERT INTO public.tracks VALUES (1532, 'Way to Blue', 4, 1712, 191);
INSERT INTO public.tracks VALUES (1533, 'Day Is Done', 5, 1712, 149);
INSERT INTO public.tracks VALUES (1534, '''Cello Song', 6, 1712, 289);
INSERT INTO public.tracks VALUES (1535, 'The Thoughts of Mary Jane', 7, 1712, 202);
INSERT INTO public.tracks VALUES (1536, 'Man in a Shed', 8, 1712, 235);
INSERT INTO public.tracks VALUES (1537, 'Fruit Tree', 9, 1712, 290);
INSERT INTO public.tracks VALUES (1538, 'Saturday Sun', 10, 1712, 243);
INSERT INTO public.tracks VALUES (1539, 'Introduction', 1, 1713, 93);
INSERT INTO public.tracks VALUES (1540, 'Hazey Jane 2', 2, 1713, 226);
INSERT INTO public.tracks VALUES (1541, 'At the Chime of a City Clock', 3, 1713, 287);
INSERT INTO public.tracks VALUES (1542, 'One of These Things First', 4, 1713, 292);
INSERT INTO public.tracks VALUES (1543, 'Hazey Jane 1', 5, 1713, 271);
INSERT INTO public.tracks VALUES (1544, 'Bryter Layter', 6, 1713, 204);
INSERT INTO public.tracks VALUES (1546, 'Poor Boy', 8, 1713, 369);
INSERT INTO public.tracks VALUES (1547, 'Northern Sky', 9, 1713, 227);
INSERT INTO public.tracks VALUES (1548, 'Sunday', 10, 1713, 222);
INSERT INTO public.tracks VALUES (1549, 'Pink Moon', 1, 1714, 126);
INSERT INTO public.tracks VALUES (1550, 'Place to Be', 2, 1714, 163);
INSERT INTO public.tracks VALUES (1551, 'Road', 3, 1714, 122);
INSERT INTO public.tracks VALUES (1552, 'Which Will', 4, 1714, 178);
INSERT INTO public.tracks VALUES (1553, 'Horn', 5, 1714, 83);
INSERT INTO public.tracks VALUES (1554, 'Things Behind the Sun', 6, 1714, 237);
INSERT INTO public.tracks VALUES (1555, 'Know', 7, 1714, 146);
INSERT INTO public.tracks VALUES (1556, 'Parasite', 8, 1714, 216);
INSERT INTO public.tracks VALUES (1463, 'Oye Cómo Va', 3, 1703, 257);
INSERT INTO public.tracks VALUES (1545, 'Fly', 7, 1713, 180);
INSERT INTO public.tracks VALUES (1557, 'Free Ride', 9, 1714, 186);
INSERT INTO public.tracks VALUES (1558, 'Harvest Breed', 10, 1714, 97);
INSERT INTO public.tracks VALUES (1478, 'Para los Rumberos', 9, 1704, 167);
INSERT INTO public.tracks VALUES (1483, 'The Gumbo Variations', 5, 1705, 773);
INSERT INTO public.tracks VALUES (1470, 'Batuka', 1, 1704, 215);
INSERT INTO public.tracks VALUES (1528, 'The Court of the Crimson King (including "Return of the Fire Witch" and "Dance of the Puppets")', 5, 1711, 566);
INSERT INTO public.tracks VALUES (1460, 'Soul Sacrifice', 9, 1702, 397);
INSERT INTO public.tracks VALUES (1477, 'Everything''s Coming Our Way', 8, 1704, 195);
INSERT INTO public.tracks VALUES (1525, 'I Talk to the Wind', 2, 1711, 364);
INSERT INTO public.tracks VALUES (1526, 'Epitaph (including "March for No Reason" and "Tomorrow and Tomorrow")', 3, 1711, 529);
INSERT INTO public.tracks VALUES (1479, 'Peaches en Regalia', 1, 1705, 218);
INSERT INTO public.tracks VALUES (1559, 'From the Morning', 11, 1714, 150);
INSERT INTO public.tracks VALUES (1560, 'Tangled Up in Blue', 1, 1715, 342);
INSERT INTO public.tracks VALUES (1561, 'Simple Twist of Fate', 2, 1715, 259);
INSERT INTO public.tracks VALUES (1562, 'You''re a Big Girl Now', 3, 1715, 276);
INSERT INTO public.tracks VALUES (1563, 'Idiot Wind', 4, 1715, 468);
INSERT INTO public.tracks VALUES (1564, 'You''re Gonna Make Me Lonesome When You Go', 5, 1715, 175);
INSERT INTO public.tracks VALUES (1565, 'Meet Me in the Morning', 6, 1715, 262);
INSERT INTO public.tracks VALUES (1566, 'Lily, Rosemary and the Jack of Hearts', 7, 1715, 531);
INSERT INTO public.tracks VALUES (1567, 'If You See Her, Say Hello', 8, 1715, 289);
INSERT INTO public.tracks VALUES (1568, 'Shelter from the Storm', 9, 1715, 302);
INSERT INTO public.tracks VALUES (1570, 'Don''t Want You No More', 1, 1716, 145);
INSERT INTO public.tracks VALUES (1571, 'It''s Not My Cross to Bear', 2, 1716, 302);
INSERT INTO public.tracks VALUES (1572, 'Black Hearted Woman', 3, 1716, 308);
INSERT INTO public.tracks VALUES (1573, 'Trouble No More', 4, 1716, 225);
INSERT INTO public.tracks VALUES (1574, 'Every Hungry Woman', 5, 1716, 253);
INSERT INTO public.tracks VALUES (1575, 'Dreams', 6, 1716, 438);
INSERT INTO public.tracks VALUES (1576, 'Whipping Post', 7, 1716, 317);
INSERT INTO public.tracks VALUES (1577, 'Revival', 1, 1717, 244);
INSERT INTO public.tracks VALUES (1578, 'Don''t Keep Me Wonderin''', 2, 1717, 220);
INSERT INTO public.tracks VALUES (1579, 'Midnight Rider', 3, 1717, 180);
INSERT INTO public.tracks VALUES (1580, 'In Memory of Elizabeth Reed', 4, 1717, 414);
INSERT INTO public.tracks VALUES (1581, 'Hoochie Coochie Man', 5, 1717, 294);
INSERT INTO public.tracks VALUES (1582, 'Please Call Home', 6, 1717, 240);
INSERT INTO public.tracks VALUES (1583, 'Leave My Blues at Home', 7, 1717, 255);
INSERT INTO public.tracks VALUES (1591, 'Ain''t Wastin'' Time No More', 1, 1719, 220);
INSERT INTO public.tracks VALUES (1592, 'Les Brers in A Minor', 2, 1719, 543);
INSERT INTO public.tracks VALUES (1595, 'One Way Out (live)', 5, 1719, 298);
INSERT INTO public.tracks VALUES (1594, 'Mountain Jam (live)', 4, 1719, 2018);
INSERT INTO public.tracks VALUES (1593, 'Melissa', 3, 1719, 234);
INSERT INTO public.tracks VALUES (1596, 'Trouble No More (live)', 6, 1719, 223);
INSERT INTO public.tracks VALUES (1597, 'Stand Back', 7, 1719, 204);
INSERT INTO public.tracks VALUES (1598, 'Blue Sky', 8, 1719, 309);
INSERT INTO public.tracks VALUES (1599, 'Little Martha', 9, 1719, 127);
INSERT INTO public.tracks VALUES (1600, 'Wasted Words', 1, 1720, 260);
INSERT INTO public.tracks VALUES (1601, 'Ramblin'' Man', 2, 1720, 288);
INSERT INTO public.tracks VALUES (1602, 'Come and Go Blues', 3, 1720, 294);
INSERT INTO public.tracks VALUES (1603, 'Jelly Jelly', 4, 1720, 346);
INSERT INTO public.tracks VALUES (1604, 'Southbound', 5, 1720, 311);
INSERT INTO public.tracks VALUES (1605, 'Jessica', 6, 1720, 451);
INSERT INTO public.tracks VALUES (1452, 'Waiting', 1, 1702, 243);
INSERT INTO public.tracks VALUES (1494, 'Shine On You Crazy Diamond (Parts VI–IX)', 5, 1707, 748);
INSERT INTO public.tracks VALUES (2302, 'Whipping Post', 7, 1718, 1360);
INSERT INTO public.tracks VALUES (1471, 'No One to Depend On', 2, 1704, 331);
INSERT INTO public.tracks VALUES (1569, 'Buckets of Rain', 10, 1715, 202);
INSERT INTO public.tracks VALUES (1606, 'Pony Boy', 7, 1720, 351);
INSERT INTO public.tracks VALUES (1588, 'You Don''t Love Me', 4, 1718, 1146);
INSERT INTO public.tracks VALUES (1590, 'In Memory of Elizabeth Reed', 6, 1718, 766);
INSERT INTO public.tracks VALUES (1584, 'Statesboro Blues', 1, 1718, 248);
INSERT INTO public.tracks VALUES (1587, 'Stormy Monday', 3, 1718, 511);
INSERT INTO public.tracks VALUES (1589, 'Hot ''Lanta', 5, 1718, 310);
INSERT INTO public.tracks VALUES (1586, 'Done Somebody Wrong', 2, 1718, 245);
INSERT INTO public.tracks VALUES (1527, 'Moonchild (including "The Dream" and "The Illusion")', 4, 1711, 733);
INSERT INTO public.tracks VALUES (1484, 'It Must Be a Camel', 6, 1705, 315);


--
-- TOC entry 3380 (class 0 OID 0)
-- Dependencies: 217
-- Name: albums_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.albums_seq', 3251, true);


--
-- TOC entry 3381 (class 0 OID 0)
-- Dependencies: 219
-- Name: artists_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.artists_seq', 1151, true);


--
-- TOC entry 3382 (class 0 OID 0)
-- Dependencies: 223
-- Name: genres_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.genres_seq', 58, true);


--
-- TOC entry 3383 (class 0 OID 0)
-- Dependencies: 222
-- Name: tracks_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tracks_seq', 2951, true);


-- Completed on 2023-11-21 13:41:17

--
-- PostgreSQL database dump complete
--

