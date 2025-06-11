package pl.bla.dev.be.backendservice.data.mock

import pl.bla.dev.be.backendservice.data.model.CityConfigDto
import pl.bla.dev.be.backendservice.data.model.CountryConfigDto
import pl.bla.dev.be.backendservice.data.model.NewTravelConfigDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentItemDto
import pl.bla.dev.be.backendservice.data.model.OnboardingContentSectionDto
import pl.bla.dev.be.backendservice.data.model.VehicleConfigDto
import pl.bla.dev.be.backendservice.data.model.VehicleTypeDto

object BEMock {
  val onboardingContent = OnboardingContentDto(
    content = listOf(
      OnboardingContentSectionDto(
        sectionId = 1,
        title = "Co najbardziej lubisz w podróżowaniu?",
        content = listOf(
          OnboardingContentItemDto(
            label = "Zwiedzanie i historia",
            valueId = 100,
          ),
          OnboardingContentItemDto(
            label = "Natura i trekking",
            valueId = 101,
          ),
          OnboardingContentItemDto(
            label = "Plażowanie",
            valueId = 102,
          ),
          OnboardingContentItemDto(
            label = "Życie nocne",
            valueId = 103,
          ),
          OnboardingContentItemDto(
            label = "Odwiedzanie miast",
            valueId = 104,
          ),
        ),
      ),
      OnboardingContentSectionDto(
        sectionId = 2,
        title = "Czym najchętniej wyruszasz w podróż?",
        content = listOf(
          OnboardingContentItemDto(
            label = "Samochód",
            valueId = 200,
          ),
          OnboardingContentItemDto(
            label = "Samolot",
            valueId = 201,
          ),
          OnboardingContentItemDto(
            label = "Pociąg",
            valueId = 202,
          ),
        ),
      ),
      OnboardingContentSectionDto(
        sectionId = 3,
        title = "Jakiej długości podróże preferujesz?",
        content = listOf(
          OnboardingContentItemDto(
            label = "1 dniowe",
            valueId = 300,
          ),
          OnboardingContentItemDto(
            label = "2 do 3 dni",
            valueId = 301,
          ),
          OnboardingContentItemDto(
            label = "4 do 7 dni",
            valueId = 302,
          ),
          OnboardingContentItemDto(
            label = "Ponad tygodniowe",
            valueId = 303,
          ),
        ),
      ),
    )
  )

  val newTravelConfig = NewTravelConfigDto(
    creatingNewTravelEnabled = true,
    countriesConfig = listOf(
      // === POLSKA ===
      CountryConfigDto(
        countryId = 1,
        continentId = 0,
        countryName = "Polska",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 101,
            cityName = "Warszawa",
            vehiclesConfig = listOf(
              VehicleConfigDto(1001, "Lotnisko Chopina (WAW)", "Główne międzynarodowe lotnisko w stolicy.", VehicleTypeDto.PLANE, "ul. Żwirki i Wigury 1, 00-906 Warszawa", 52.1657, 20.9671),
              VehicleConfigDto(1002, "Dworzec Warszawa Centralna", "Główny dworzec kolejowy w centrum miasta.", VehicleTypeDto.TRAIN, "Aleje Jerozolimskie 54, 00-024 Warszawa", 52.2281, 21.0032),
              VehicleConfigDto(1003, "Dworzec Autobusowy Warszawa Zachodnia", "Główny dworzec autobusów dalekobieżnych.", VehicleTypeDto.BUS, "Aleje Jerozolimskie 144, 02-305 Warszawa", 52.2185, 20.9628),
              VehicleConfigDto(1004, "Punkt wynajmu aut Centrum", "Odbiór samochodów w centrum miasta.", VehicleTypeDto.CAR, "ul. Złota 59, 00-120 Warszawa", 52.2318, 21.0028)
            )
          ),
          CityConfigDto(
            cityId = 102,
            cityName = "Kraków",
            vehiclesConfig = listOf(
              VehicleConfigDto(1005, "Port Lotniczy Kraków-Balice (KRK)", "Międzynarodowe lotnisko obsługujące Kraków.", VehicleTypeDto.PLANE, "kpt. Mieczysława Medweckiego 1, 32-083 Balice", 50.0777, 19.7848),
              VehicleConfigDto(1006, "Dworzec Kraków Główny", "Główny dworzec kolejowy zintegrowany z galerią handlową.", VehicleTypeDto.TRAIN, "ul. Pawia 5a, 31-154 Kraków", 50.0685, 19.9481),
              VehicleConfigDto(1007, "Małopolski Dworzec Autobusowy (MDA)", "Główny dworzec autobusowy obok stacji kolejowej.", VehicleTypeDto.BUS, "ul. Bosacka 18, 31-505 Kraków", 50.0679, 19.9501),
              VehicleConfigDto(1008, "Punkt wynajmu aut Stare Miasto", "Odbiór samochodów w pobliżu Starego Miasta.", VehicleTypeDto.CAR, "ul. Szewska 2, 31-009 Kraków", 50.0621, 19.9360)
            )
          )
        )
      ),
      // === NIEMCY ===
      CountryConfigDto(
        countryId = 2,
        continentId = 0,
        countryName = "Niemcy",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 201,
            cityName = "Berlin",
            vehiclesConfig = listOf(
              VehicleConfigDto(2001, "Port Lotniczy Berlin-Brandenburg (BER)", "Główne lotnisko dla Berlina i okolic.", VehicleTypeDto.PLANE, "Melli-Beese-Ring 1, 12529 Schönefeld", 52.3667, 13.5017),
              VehicleConfigDto(2002, "Berlin Hauptbahnhof", "Centralny dworzec kolejowy Berlina.", VehicleTypeDto.TRAIN, "Europaplatz 1, 10557 Berlin", 52.5250, 13.3694),
              VehicleConfigDto(2003, "Zentraler Omnibusbahnhof Berlin (ZOB)", "Centralny dworzec autobusowy.", VehicleTypeDto.BUS, "Masurenallee 4-6, 14057 Berlin", 52.5074, 13.2790),
              VehicleConfigDto(2004, "Punkt wynajmu aut Mitte", "Odbiór samochodów w dzielnicy Mitte.", VehicleTypeDto.CAR, "Friedrichstraße 14, 10117 Berlin", 52.5186, 13.3888)
            )
          ),
          CityConfigDto(
            cityId = 202,
            cityName = "Monachium",
            vehiclesConfig = listOf(
              VehicleConfigDto(2005, "Port Lotniczy Monachium (MUC)", "Drugie co do wielkości lotnisko w Niemczech.", VehicleTypeDto.PLANE, "Nordallee 25, 85356 Freising", 48.3537, 11.7861),
              VehicleConfigDto(2006, "München Hauptbahnhof", "Główny dworzec kolejowy w Monachium.", VehicleTypeDto.TRAIN, "Bayerstraße 10A, 80335 München", 48.1402, 11.5583),
              VehicleConfigDto(2007, "Zentraler Omnibusbahnhof München (ZOB)", "Centralny dworzec autobusowy w Monachium.", VehicleTypeDto.BUS, "Hackerbrücke 4, 80335 München", 48.1432, 11.5498),
              VehicleConfigDto(2008, "Punkt wynajmu aut Altstadt", "Odbiór samochodów na starym mieście.", VehicleTypeDto.CAR, "Marienplatz 1, 80331 München", 48.1372, 11.5755)
            )
          )
        )
      ),
      // === FRANCJA ===
      CountryConfigDto(
        countryId = 3,
        continentId = 0,
        countryName = "Francja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 301,
            cityName = "Paryż",
            vehiclesConfig = listOf(
              VehicleConfigDto(3001, "Port Lotniczy Charles de Gaulle (CDG)", "Największe międzynarodowe lotnisko we Francji.", VehicleTypeDto.PLANE, "95700 Roissy-en-France", 49.0097, 2.5479),
              VehicleConfigDto(3002, "Gare du Nord", "Jeden z głównych dworców kolejowych w Paryżu.", VehicleTypeDto.TRAIN, "18 Rue de Dunkerque, 75010 Paris", 48.8809, 2.3553),
              VehicleConfigDto(3003, "Gare Routière de Paris-Bercy", "Główny dworzec autobusowy dla połączeń międzynarodowych.", VehicleTypeDto.BUS, "208 Quai de Bercy, 75012 Paris", 48.8398, 2.3809),
              VehicleConfigDto(3004, "Punkt wynajmu aut Luwr", "Odbiór samochodów w pobliżu Luwru.", VehicleTypeDto.CAR, "1 Rue de l'Amiral de Coligny, 75001 Paris", 48.8606, 2.3402)
            )
          ),
          CityConfigDto(
            cityId = 302,
            cityName = "Nicea",
            vehiclesConfig = listOf(
              VehicleConfigDto(3005, "Port Lotniczy Nicea-Lazurowe Wybrzeże (NCE)", "Główne lotnisko na Lazurowym Wybrzeżu.", VehicleTypeDto.PLANE, "Rue Costes et Bellonte, 06206 Nice", 43.6653, 7.2150),
              VehicleConfigDto(3006, "Gare de Nice-Ville", "Główny dworzec kolejowy w Nicei.", VehicleTypeDto.TRAIN, "Avenue Thiers, 06000 Nice", 43.7042, 7.2625),
              VehicleConfigDto(3007, "Gare Routière de Nice", "Dworzec autobusowy blisko starego miasta.", VehicleTypeDto.BUS, "16 Av. des Diables Bleus, 06300 Nice", 43.7081, 7.2885),
              VehicleConfigDto(3008, "Punkt wynajmu aut Promenade", "Odbiór aut przy Promenadzie Anglików.", VehicleTypeDto.CAR, "1 Prom. des Anglais, 06000 Nice", 43.6948, 7.2560)
            )
          )
        )
      ),
      // === HISZPANIA ===
      CountryConfigDto(
        countryId = 4,
        continentId = 0,
        countryName = "Hiszpania",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 401,
            cityName = "Madryt",
            vehiclesConfig = listOf(
              VehicleConfigDto(4001, "Port Lotniczy Madryt-Barajas (MAD)", "Główne lotnisko w Hiszpanii.", VehicleTypeDto.PLANE, "Av de la Hispanidad, 28042 Madrid", 40.4983, -3.5676),
              VehicleConfigDto(4002, "Estación de Atocha", "Największy dworzec kolejowy w Madrycie.", VehicleTypeDto.TRAIN, "Pl. del Emperador Carlos V, 28045 Madrid", 40.4063, -3.6903),
              VehicleConfigDto(4003, "Estación Sur de Autobuses", "Główny dworzec autobusowy w Madrycie.", VehicleTypeDto.BUS, "Calle de Méndez Álvaro, 83, 28045 Madrid", 40.3986, -3.6826),
              VehicleConfigDto(4004, "Punkt wynajmu aut Gran Vía", "Odbiór aut przy głównej ulicy Madrytu.", VehicleTypeDto.CAR, "Gran Vía, 48, 28013 Madrid", 40.4208, -3.7068)
            )
          ),
          CityConfigDto(
            cityId = 402,
            cityName = "Barcelona",
            vehiclesConfig = listOf(
              VehicleConfigDto(4005, "Port Lotniczy Barcelona-El Prat (BCN)", "Główne lotnisko dla Katalonii.", VehicleTypeDto.PLANE, "08820 El Prat de Llobregat, Barcelona", 41.2974, 2.0833),
              VehicleConfigDto(4006, "Estació de Sants", "Główny dworzec kolejowy Barcelony.", VehicleTypeDto.TRAIN, "Plaça dels Països Catalans, 1, 7, 08014 Barcelona", 41.3790, 2.1398),
              VehicleConfigDto(4007, "Estació d'Autobusos de Sants", "Dworzec autobusowy przy stacji kolejowej Sants.", VehicleTypeDto.BUS, "Carrer de Viriat, 45, 08014 Barcelona", 41.3781, 2.1417),
              VehicleConfigDto(4008, "Punkt wynajmu aut Plaça de Catalunya", "Odbiór aut w centralnym punkcie miasta.", VehicleTypeDto.CAR, "Plaça de Catalunya, 1, 08002 Barcelona", 41.3874, 2.1700)
            )
          )
        )
      ),
      // === WŁOCHY ===
      CountryConfigDto(
        countryId = 5,
        continentId = 0,
        countryName = "Włochy",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 501,
            cityName = "Rzym",
            vehiclesConfig = listOf(
              VehicleConfigDto(5001, "Port Lotniczy Rzym-Fiumicino (FCO)", "Główne lotnisko Rzymu.", VehicleTypeDto.PLANE, "Via dell' Aeroporto di Fiumicino, 00054 Fiumicino RM", 41.8003, 12.2389),
              VehicleConfigDto(5002, "Stazione Termini", "Główny dworzec kolejowy i przesiadkowy Rzymu.", VehicleTypeDto.TRAIN, "Piazza dei Cinquecento, 00185 Roma RM", 41.9015, 12.5013),
              VehicleConfigDto(5003, "Autostazione Tibus", "Główny terminal autobusów dalekobieżnych.", VehicleTypeDto.BUS, "Largo Guido Mazzoni, 00162 Roma RM", 41.9109, 12.5259),
              VehicleConfigDto(5004, "Punkt wynajmu aut Koloseum", "Odbiór aut w pobliżu Koloseum.", VehicleTypeDto.CAR, "Via Celimontana, 15, 00184 Roma RM", 41.8892, 12.4922)
            )
          ),
          CityConfigDto(
            cityId = 502,
            cityName = "Mediolan",
            vehiclesConfig = listOf(
              VehicleConfigDto(5005, "Port Lotniczy Mediolan-Malpensa (MXP)", "Największe lotnisko w regionie Mediolanu.", VehicleTypeDto.PLANE, "21010 Ferno, VA", 45.6306, 8.7281),
              VehicleConfigDto(5006, "Milano Centrale", "Główny dworzec kolejowy Mediolanu.", VehicleTypeDto.TRAIN, "Piazza Duca d'Aosta, 1, 20124 Milano MI", 45.4862, 9.2040),
              VehicleConfigDto(5007, "Autostazione di Lampugnano", "Główny terminal autobusowy.", VehicleTypeDto.BUS, "Via Giulio Natta, 226, 20151 Milano MI", 45.4907, 9.1238),
              VehicleConfigDto(5008, "Punkt wynajmu aut Duomo", "Odbiór aut przy Katedrze Duomo.", VehicleTypeDto.CAR, "Piazza del Duomo, 20122 Milano MI", 45.4642, 9.1900)
            )
          )
        )
      ),
      // === ALBANIA ===
      CountryConfigDto(
        countryId = 6,
        continentId = 0,
        countryName = "Albania",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 601,
            cityName = "Tirana",
            vehiclesConfig = listOf(
              VehicleConfigDto(6001, "Port Lotniczy Tirana (TIA)", "Międzynarodowe lotnisko im. Matki Teresy.", VehicleTypeDto.PLANE, "Rinas, Tirana", 41.4147, 19.7206),
              VehicleConfigDto(6002, "Stacioni i Trenit Tiranë", "Dworzec kolejowy w Tiranie (w trakcie modernizacji).", VehicleTypeDto.TRAIN, "Bulevardi Zogu I, Tiranë", 41.3345, 19.8183),
              VehicleConfigDto(6003, "Terminali i Autobusave Ndërkombëtar", "Międzynarodowy terminal autobusowy.", VehicleTypeDto.BUS, "Rruga Dritan Hoxha, Tiranë", 41.3389, 19.7915),
              VehicleConfigDto(6004, "Punkt wynajmu aut Sheshi Skënderbej", "Odbiór samochodów na Placu Skanderbega.", VehicleTypeDto.CAR, "Sheshi Skënderbej, Tiranë", 41.3275, 19.8187)
            )
          ),
          CityConfigDto(
            cityId = 602,
            cityName = "Durrës",
            vehiclesConfig = listOf(
              VehicleConfigDto(6005, "Port Lotniczy Tirana (TIA)", "Najbliższe lotnisko, obsługujące również Durrës.", VehicleTypeDto.PLANE, "Rinas, Tirana", 41.4147, 19.7206),
              VehicleConfigDto(6006, "Stacioni i Trenit Durrës", "Główny dworzec kolejowy w Durrës.", VehicleTypeDto.TRAIN, "Rruga Adria, Durrës", 41.3218, 19.4526),
              VehicleConfigDto(6007, "Terminali i Autobusave Durrës", "Dworzec autobusowy blisko portu.", VehicleTypeDto.BUS, "Rruga Pavarësia, Durrës", 41.3168, 19.4497),
              VehicleConfigDto(6008, "Punkt wynajmu aut Port", "Odbiór samochodów w pobliżu portu morskiego.", VehicleTypeDto.CAR, "Rruga Doganës, Durrës", 41.3117, 19.4449)
            )
          )
        )
      ),
// === ANDORA ===
      CountryConfigDto(
        countryId = 7,
        continentId = 0,
        countryName = "Andora",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 701,
            cityName = "Andora la Vella",
            vehiclesConfig = listOf(
              VehicleConfigDto(7001, "Lotnisko Barcelona-El Prat (BCN), Hiszpania", "Najbliższe duże lotnisko międzynarodowe.", VehicleTypeDto.PLANE, "08820 El Prat de Llobregat, Barcelona", 41.2974, 2.0833),
              VehicleConfigDto(7002, "Dworzec L'Hospitalet-près-l'Andorre, Francja", "Najbliższa stacja kolejowa z połączeniami międzynarodowymi.", VehicleTypeDto.TRAIN, "Rue de la Gare, 09390 L'Hospitalet-près-l'Andorre", 42.5925, 1.8319),
              VehicleConfigDto(7003, "Estació Nacional d'Autobusos", "Krajowy dworzec autobusowy Andory.", VehicleTypeDto.BUS, "Carrer de la Cúria, AD500 Andorra la Vella", 42.5060, 1.5192),
              VehicleConfigDto(7004, "Punkt wynajmu aut Centre", "Odbiór samochodów w centrum stolicy.", VehicleTypeDto.CAR, "Avinguda de Tarragona, AD500 Andorra la Vella", 42.5045, 1.5204)
            )
          ),
          CityConfigDto(
            cityId = 702,
            cityName = "Escaldes-Engordany",
            vehiclesConfig = listOf(
              VehicleConfigDto(7005, "Lotnisko Toulouse–Blagnac (TLS), Francja", "Alternatywne lotnisko w pobliżu Andory.", VehicleTypeDto.PLANE, "Cornebarrieu, 31703 Blagnac", 43.6293, 1.3638),
              VehicleConfigDto(7006, "Dworzec Latour-de-Carol, Francja", "Alternatywna stacja kolejowa blisko granicy.", VehicleTypeDto.TRAIN, "Avenue de la Gare, 66760 Latour-de-Carol", 42.4936, 1.8981),
              VehicleConfigDto(7007, "Przystanek autobusowy Caldea", "Główny przystanek autobusowy w mieście.", VehicleTypeDto.BUS, "Avinguda Carlemany, 10, AD700 Escaldes-Engordany", 42.5097, 1.5368),
              VehicleConfigDto(7008, "Punkt wynajmu aut Caldea", "Odbiór samochodów przy centrum spa Caldea.", VehicleTypeDto.CAR, "Parc de la Mola, 10, AD700 Escaldes-Engordany", 42.5113, 1.5375)
            )
          )
        )
      ),
// === ARMENIA ===
      CountryConfigDto(
        countryId = 8,
        continentId = 0,
        countryName = "Armenia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 801,
            cityName = "Erywań",
            vehiclesConfig = listOf(
              VehicleConfigDto(8001, "Międzynarodowy Port Lotniczy Zvartnots (EVN)", "Główne lotnisko Armenii.", VehicleTypeDto.PLANE, "Yerevan, 0042", 40.1473, 44.3959),
              VehicleConfigDto(8002, "Dworzec kolejowy Sasuntsi Davit", "Główny dworzec kolejowy w Erywaniu.", VehicleTypeDto.TRAIN, "Sasuntsi Davit Square, Yerevan", 40.1578, 44.5072),
              VehicleConfigDto(8003, "Dworzec autobusowy Kilikia", "Centralny dworzec autobusowy w Erywaniu.", VehicleTypeDto.BUS, "Admiral Isakov Ave, Yerevan", 40.1708, 44.4858),
              VehicleConfigDto(8004, "Punkt wynajmu aut Plac Republiki", "Odbiór samochodów w sercu miasta.", VehicleTypeDto.CAR, "Republic Square, Yerevan", 40.1776, 44.5126)
            )
          ),
          CityConfigDto(
            cityId = 802,
            cityName = "Giumri",
            vehiclesConfig = listOf(
              VehicleConfigDto(8005, "Port Lotniczy Shirak (LWN)", "Drugie co do wielkości lotnisko w Armenii.", VehicleTypeDto.PLANE, "5821, Gyumri", 40.7509, 43.8593),
              VehicleConfigDto(8006, "Dworzec kolejowy w Giumri", "Ważny węzeł kolejowy w północnej Armenii.", VehicleTypeDto.TRAIN, "Garegin Nzhdeh Ave, Gyumri", 40.8037, 43.8394),
              VehicleConfigDto(8007, "Dworzec autobusowy Giumri", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Tigran Mets St, Gyumri", 40.7852, 43.8519),
              VehicleConfigDto(8008, "Punkt wynajmu aut Plac Vardananc", "Odbiór samochodów na głównym placu.", VehicleTypeDto.CAR, "Vardanants Square, Gyumri", 40.7858, 43.8452)
            )
          )
        )
      ),
// === AUSTRIA ===
      CountryConfigDto(
        countryId = 9,
        continentId = 0,
        countryName = "Austria",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 901,
            cityName = "Wiedeń",
            vehiclesConfig = listOf(
              VehicleConfigDto(9001, "Port Lotniczy Wiedeń-Schwechat (VIE)", "Główne międzynarodowe lotnisko Austrii.", VehicleTypeDto.PLANE, "1300 Schwechat", 48.1103, 16.5697),
              VehicleConfigDto(9002, "Wien Hauptbahnhof", "Główny, nowoczesny dworzec kolejowy Wiednia.", VehicleTypeDto.TRAIN, "Am Hauptbahnhof 1, 1100 Wien", 48.1852, 16.3761),
              VehicleConfigDto(9003, "Vienna International Busterminal (VIB)", "Główny terminal autobusów międzynarodowych.", VehicleTypeDto.BUS, "Erdbergstraße 200 A, 1030 Wien", 48.1925, 16.4026),
              VehicleConfigDto(9004, "Punkt wynajmu aut Innere Stadt", "Odbiór samochodów w historycznym centrum.", VehicleTypeDto.CAR, "Kärntner Ring 12, 1010 Wien", 48.2033, 16.3718)
            )
          ),
          CityConfigDto(
            cityId = 902,
            cityName = "Salzburg",
            vehiclesConfig = listOf(
              VehicleConfigDto(9005, "Port Lotniczy Salzburg (SZG)", "Lotnisko im. W.A. Mozarta.", VehicleTypeDto.PLANE, "Innsbrucker Bundesstraße 95, 5020 Salzburg", 47.7933, 13.0043),
              VehicleConfigDto(9006, "Salzburg Hauptbahnhof", "Główny dworzec kolejowy Salzburga.", VehicleTypeDto.TRAIN, "Südtiroler Platz 1, 5020 Salzburg", 47.8129, 13.0458),
              VehicleConfigDto(9007, "Salzburg Terminal Bus", "Główny terminal autobusowy przy dworcu kolejowym.", VehicleTypeDto.BUS, "Lastenstraße 12, 5020 Salzburg", 47.8143, 13.0463),
              VehicleConfigDto(9008, "Punkt wynajmu aut Altstadt", "Odbiór samochodów w pobliżu Starego Miasta.", VehicleTypeDto.CAR, "Mirabellplatz 2, 5020 Salzburg", 47.8062, 13.0427)
            )
          )
        )
      ),
      // === AZERBEJDŻAN ===
      CountryConfigDto(
        countryId = 10,
        continentId = 0,
        countryName = "Azerbejdżan",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1001,
            cityName = "Baku",
            vehiclesConfig = listOf(
              VehicleConfigDto(10001, "Międzynarodowy Port Lotniczy Hejdara Alijewa (GYD)", "Główne lotnisko i węzeł komunikacyjny Azerbejdżanu.", VehicleTypeDto.PLANE, "Baku, AZ1044", 40.4675, 50.0471),
              VehicleConfigDto(10002, "Dworzec kolejowy w Baku (Baku-Passazhirskaya)", "Centralna stacja kolejowa stolicy.", VehicleTypeDto.TRAIN, "28 May St, Baku", 40.3792, 49.8531),
              VehicleConfigDto(10003, "Międzynarodowy Dworzec Autobusowy w Baku", "Nowoczesny kompleks obsługujący trasy krajowe i międzynarodowe.", VehicleTypeDto.BUS, "Sumqayit Hwy, Baku", 40.4371, 49.7899),
              VehicleConfigDto(10004, "Punkt wynajmu aut Flame Towers", "Odbiór samochodów w pobliżu ikonicznych wieżowców.", VehicleTypeDto.CAR, "Parliament Ave, Baku", 40.3601, 49.8280)
            )
          ),
          CityConfigDto(
            cityId = 1002,
            cityName = "Gandża",
            vehiclesConfig = listOf(
              VehicleConfigDto(10005, "Międzynarodowy Port Lotniczy Gandża (KVD)", "Drugie co do wielkości lotnisko w kraju.", VehicleTypeDto.PLANE, "Ganja-Samukh highway, Ganja", 40.7325, 46.3183),
              VehicleConfigDto(10006, "Dworzec kolejowy w Gandży", "Główna stacja kolejowa w mieście.", VehicleTypeDto.TRAIN, "Ataturk Ave, Ganja", 40.6715, 46.3764),
              VehicleConfigDto(10007, "Dworzec autobusowy w Gandży", "Obsługuje połączenia regionalne i krajowe.", VehicleTypeDto.BUS, "Shah Ismayil Khatai Ave, Ganja", 40.6942, 46.3551),
              VehicleConfigDto(10008, "Punkt wynajmu aut Centrum", "Odbiór samochodów w centrum miasta.", VehicleTypeDto.CAR, "Javad Khan St, Ganja", 40.6833, 46.3611)
            )
          )
        )
      ),
// === BELGIA ===
      CountryConfigDto(
        countryId = 11,
        continentId = 0,
        countryName = "Belgia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1101,
            cityName = "Bruksela",
            vehiclesConfig = listOf(
              VehicleConfigDto(11001, "Port Lotniczy Bruksela (BRU)", "Główne lotnisko Belgii w Zaventem.", VehicleTypeDto.PLANE, "Leopoldlaan, 1930 Zaventem", 50.9014, 4.4856),
              VehicleConfigDto(11002, "Dworzec Bruxelles-Midi/Brussel-Zuid", "Główny dworzec dla pociągów międzynarodowych (Eurostar, Thalys).", VehicleTypeDto.TRAIN, "Avenue Fonsny 47B, 1060 Brussel", 50.8354, 4.3355),
              VehicleConfigDto(11003, "Dworzec autobusowy Bruxelles-Nord", "Główny hub dla autobusów dalekobieżnych.", VehicleTypeDto.BUS, "Rue du Progrès 80, 1030 Brussel", 50.8601, 4.3608),
              VehicleConfigDto(11004, "Punkt wynajmu aut Grand-Place", "Odbiór aut w sercu historycznego centrum.", VehicleTypeDto.CAR, "Rue du Marché aux Herbes 8, 1000 Brussel", 50.8471, 4.3525)
            )
          ),
          CityConfigDto(
            cityId = 1102,
            cityName = "Brugia",
            vehiclesConfig = listOf(
              VehicleConfigDto(11005, "Port Lotniczy Bruksela (BRU)", "Najbliższe duże lotnisko międzynarodowe, obsługujące Brugię.", VehicleTypeDto.PLANE, "Leopoldlaan, 1930 Zaventem", 50.9014, 4.4856),
              VehicleConfigDto(11006, "Station Brugge", "Główny dworzec kolejowy miasta.", VehicleTypeDto.TRAIN, "Stationsplein, 8000 Brugge", 51.1972, 3.2173),
              VehicleConfigDto(11007, "Dworzec autobusowy Station Brugge", "Terminal autobusowy zintegrowany z dworcem kolejowym.", VehicleTypeDto.BUS, "Stationsplein, 8000 Brugge", 51.1970, 3.2178),
              VehicleConfigDto(11008, "Punkt wynajmu aut 't Zand", "Odbiór samochodów przy głównym placu 't Zand.", VehicleTypeDto.CAR, "Kon. Albert I-laan, 8200 Brugge", 51.2023, 3.2185)
            )
          )
        )
      ),
// === BIAŁORUŚ ===
      CountryConfigDto(
        countryId = 12,
        continentId = 0,
        countryName = "Białoruś",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1201,
            cityName = "Mińsk",
            vehiclesConfig = listOf(
              VehicleConfigDto(12001, "Narodowy Port Lotniczy Mińsk (MSQ)", "Główne międzynarodowe lotnisko Białorusi.", VehicleTypeDto.PLANE, "Minsk, 220054", 53.8825, 28.0307),
              VehicleConfigDto(12002, "Dworzec Mińsk Pasażerski", "Główny dworzec kolejowy stolicy.", VehicleTypeDto.TRAIN, "Privokzalnaya Square 3, Minsk", 53.8906, 27.5508),
              VehicleConfigDto(12003, "Centralny Dworzec Autobusowy", "Nowoczesny dworzec zintegrowany z kolejowym.", VehicleTypeDto.BUS, "Babrujskaja St 6, Minsk", 53.8901, 27.5529),
              VehicleConfigDto(12004, "Punkt wynajmu aut Plac Niepodległości", "Odbiór samochodów w centrum administracyjnym miasta.", VehicleTypeDto.CAR, "Niezaliežnasci Ave 11, Minsk", 53.8949, 27.5480)
            )
          ),
          CityConfigDto(
            cityId = 1202,
            cityName = "Grodno",
            vehiclesConfig = listOf(
              VehicleConfigDto(12005, "Narodowy Port Lotniczy Mińsk (MSQ)", "Główne lotnisko kraju, obsługujące również Grodno.", VehicleTypeDto.PLANE, "Minsk, 220054", 53.8825, 28.0307),
              VehicleConfigDto(12006, "Dworzec kolejowy w Grodnie", "Ważny dworzec blisko granicy z Polską i Litwą.", VehicleTypeDto.TRAIN, "Budzonnaha St 37, Grodno", 53.6811, 23.8436),
              VehicleConfigDto(12007, "Dworzec autobusowy w Grodnie", "Obsługuje trasy regionalne i międzynarodowe.", VehicleTypeDto.BUS, "Krasnoarmeyskaya St 7А, Grodno", 53.6766, 23.8398),
              VehicleConfigDto(12008, "Punkt wynajmu aut ul. Sowiecka", "Odbiór samochodów przy głównym deptaku miasta.", VehicleTypeDto.CAR, "Savieckaja St 1, Grodno", 53.6835, 23.8306)
            )
          )
        )
      ),
// === BOŚNIA I HERCEGOWINA ===
      CountryConfigDto(
        countryId = 13,
        continentId = 0,
        countryName = "Bośnia i Hercegowina",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1301,
            cityName = "Sarajewo",
            vehiclesConfig = listOf(
              VehicleConfigDto(13001, "Międzynarodowy Port Lotniczy Sarajewo (SJJ)", "Główne lotnisko kraju.", VehicleTypeDto.PLANE, "Kurta Schorka 36, Sarajevo 71210", 43.8246, 18.3315),
              VehicleConfigDto(13002, "Željeznička stanica Sarajevo", "Główny dworzec kolejowy w Sarajewie.", VehicleTypeDto.TRAIN, "Put života 2, Sarajevo 71000", 43.8587, 18.3958),
              VehicleConfigDto(13003, "Glavna autobuska stanica", "Główny dworzec autobusowy dla tras federacyjnych.", VehicleTypeDto.BUS, "Put života 8, Sarajevo 71000", 43.8589, 18.3970),
              VehicleConfigDto(13004, "Punkt wynajmu aut Baščaršija", "Odbiór samochodów w sercu starego miasta.", VehicleTypeDto.CAR, "Mula Mustafe Bašeskije 6, Sarajevo 71000", 43.8596, 18.4312)
            )
          ),
          CityConfigDto(
            cityId = 1302,
            cityName = "Mostar",
            vehiclesConfig = listOf(
              VehicleConfigDto(13005, "Międzynarodowy Port Lotniczy Mostar (OMO)", "Lotnisko obsługujące region Hercegowiny.", VehicleTypeDto.PLANE, "Ortiješ bb, Mostar 88000", 43.2830, 17.8459),
              VehicleConfigDto(13006, "Željeznička stanica Mostar", "Dworzec kolejowy z malowniczą trasą do Sarajewa.", VehicleTypeDto.TRAIN, "Trg Ivana Krndelja 1, Mostar 88000", 43.3486, 17.8109),
              VehicleConfigDto(13007, "Autobusna stanica Mostar", "Dworzec autobusowy, podzielony na część wschodnią i zachodnią.", VehicleTypeDto.BUS, "Trg Ivana Krndelja, Mostar 88000", 43.3484, 17.8115),
              VehicleConfigDto(13008, "Punkt wynajmu aut Stari Most", "Odbiór samochodów w pobliżu Starego Mostu.", VehicleTypeDto.CAR, "Goce Delčeva, Mostar 88000", 43.3379, 17.8139)
            )
          )
        )
      ),
      // === BUŁGARIA ===
      CountryConfigDto(
        countryId = 14,
        continentId = 0,
        countryName = "Bułgaria",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1401,
            cityName = "Sofia",
            vehiclesConfig = listOf(
              VehicleConfigDto(14001, "Port Lotniczy Sofia (SOF)", "Główne międzynarodowe lotnisko Bułgarii.", VehicleTypeDto.PLANE, "bul. Christopher Columbus 1, 1540 Sofia", 42.6953, 23.4114),
              VehicleConfigDto(14002, "Centralna Gara Sofia", "Centralny dworzec kolejowy Sofii.", VehicleTypeDto.TRAIN, "bul. Knyaginya Maria Luiza 102, 1202 Sofia", 42.7141, 23.3223),
              VehicleConfigDto(14003, "Centralna Avtogara Sofia", "Główny dworzec autobusowy, obok stacji kolejowej.", VehicleTypeDto.BUS, "bul. Knyaginya Maria Luiza 100, 1202 Sofia", 42.7126, 23.3225),
              VehicleConfigDto(14004, "Punkt wynajmu aut Vitosha", "Odbiór samochodów przy głównym deptaku miasta.", VehicleTypeDto.CAR, "bul. Vitosha 62, 1000 Sofia", 42.6905, 23.3201)
            )
          ),
          CityConfigDto(
            cityId = 1402,
            cityName = "Burgas",
            vehiclesConfig = listOf(
              VehicleConfigDto(14005, "Port Lotniczy Burgas (BOJ)", "Lotnisko obsługujące kurorty na południowym wybrzeżu.", VehicleTypeDto.PLANE, "8016 Burgas", 42.5696, 27.5152),
              VehicleConfigDto(14006, "Gara Burgas", "Dworzec kolejowy w Burgas.", VehicleTypeDto.TRAIN, "pl. Tsaritsa Yoanna, 8000 Burgas", 42.4936, 27.4764),
              VehicleConfigDto(14007, "Avtogara Yug (Południe)", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "bul. Ivan Vazov 1, 8000 Burgas", 42.4928, 27.4754),
              VehicleConfigDto(14008, "Punkt wynajmu aut Morska Gradina", "Odbiór aut w pobliżu Parku Nadmorskiego.", VehicleTypeDto.CAR, "bul. Demokratsia 1, 8000 Burgas", 42.5029, 27.4728)
            )
          )
        )
      ),
// === CHORWACJA ===
      CountryConfigDto(
        countryId = 15,
        continentId = 0,
        countryName = "Chorwacja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1501,
            cityName = "Zagrzeb",
            vehiclesConfig = listOf(
              VehicleConfigDto(15001, "Port Lotniczy Zagrzeb (ZAG)", "Lotnisko im. Franjo Tuđmana, główne lotnisko kraju.", VehicleTypeDto.PLANE, "Ul. Rudolfa Fizira 1, 10150, Velika Gorica", 45.7429, 16.0688),
              VehicleConfigDto(15002, "Zagreb Glavni kolodvor", "Główny dworzec kolejowy Zagrzebia.", VehicleTypeDto.TRAIN, "Trg kralja Tomislava 12, 10000 Zagreb", 45.8055, 15.9785),
              VehicleConfigDto(15003, "Autobusni kolodvor Zagreb", "Główny dworzec autobusowy.", VehicleTypeDto.BUS, "Avenija Marina Držića 4, 10000 Zagreb", 45.8037, 15.9943),
              VehicleConfigDto(15004, "Punkt wynajmu aut Plac bana Jelačicia", "Odbiór aut na głównym placu miasta.", VehicleTypeDto.CAR, "Trg bana Josipa Jelačića 1, 10000 Zagreb", 45.8131, 15.9772)
            )
          ),
          CityConfigDto(
            cityId = 1502,
            cityName = "Dubrownik",
            vehiclesConfig = listOf(
              VehicleConfigDto(15005, "Port Lotniczy Dubrownik (DBV)", "Lotnisko Čilipi, brama do południowej Dalmacji.", VehicleTypeDto.PLANE, "Čilipi, 20213, Konavle", 42.5614, 18.2682),
              VehicleConfigDto(15006, "Brak bezpośrednich połączeń", "Dubrownik nie posiada stacji kolejowej.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(15007, "Autobusni kolodvor Dubrovnik", "Główny dworzec autobusowy w porcie Gruž.", VehicleTypeDto.BUS, "Obala pape Ivana Pavla II 44 A, 20000 Dubrovnik", 42.6627, 18.0827),
              VehicleConfigDto(15008, "Punkt wynajmu aut Stare Miasto", "Odbiór samochodów przy bramie Pile.", VehicleTypeDto.CAR, "Branitelja Dubrovnika 1, 20000 Dubrovnik", 42.6420, 18.1064)
            )
          )
        )
      ),
// === CYPR ===
      CountryConfigDto(
        countryId = 16,
        continentId = 0,
        countryName = "Cypr",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1601,
            cityName = "Nikozja",
            vehiclesConfig = listOf(
              VehicleConfigDto(16001, "Port Lotniczy Larnaka (LCA)", "Główne lotnisko Cypru, najbliżej stolicy.", VehicleTypeDto.PLANE, "Larnaca, 6650", 34.8751, 33.6249),
              VehicleConfigDto(16002, "Brak połączeń kolejowych", "Na Cyprze nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(16003, "Dworzec autobusowy Solomou Square", "Główny węzeł autobusowy w Nikozji.", VehicleTypeDto.BUS, "Plac Solomou, Nikozja", 35.1738, 33.3591),
              VehicleConfigDto(16004, "Punkt wynajmu aut Ledra Street", "Odbiór aut w pobliżu głównej ulicy handlowej.", VehicleTypeDto.CAR, "Ledra Street 10, Nikozja 1011", 35.1751, 33.3613)
            )
          ),
          CityConfigDto(
            cityId = 1602,
            cityName = "Pafos",
            vehiclesConfig = listOf(
              VehicleConfigDto(16005, "Międzynarodowy Port Lotniczy Pafos (PFO)", "Lotnisko obsługujące zachodnią część Cypru.", VehicleTypeDto.PLANE, "Pafos, 8507", 34.7180, 32.4857),
              VehicleConfigDto(16006, "Brak połączeń kolejowych", "Na Cyprze nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(16007, "Dworzec autobusowy Karavella", "Główny dworzec autobusowy w górnej części miasta.", VehicleTypeDto.BUS, "Andrea Geroudi 13, Paphos 8010", 34.7797, 32.4277),
              VehicleConfigDto(16008, "Punkt wynajmu aut Kato Paphos", "Odbiór samochodów w pobliżu portu i strefy archeologicznej.", VehicleTypeDto.CAR, "Apostolou Pavlou Ave, Paphos 8046", 34.7570, 32.4143)
            )
          )
        )
      ),
// === CZARNOGÓRA ===
      CountryConfigDto(
        countryId = 17,
        continentId = 0,
        countryName = "Czarnogóra",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1701,
            cityName = "Podgorica",
            vehiclesConfig = listOf(
              VehicleConfigDto(17001, "Port Lotniczy Podgorica (TGD)", "Główne międzynarodowe lotnisko Czarnogóry.", VehicleTypeDto.PLANE, "Golubovci, 81000", 42.3594, 19.2519),
              VehicleConfigDto(17002, "Željeznička stanica Podgorica", "Główny dworzec kolejowy, część linii Belgrad-Bar.", VehicleTypeDto.TRAIN, "Trg Golootočkih Žrtava 1, 81000 Podgorica", 42.4344, 19.2662),
              VehicleConfigDto(17003, "Autobuska stanica Podgorica", "Główny dworzec autobusowy, obok kolejowego.", VehicleTypeDto.BUS, "Trg Golootočkih Žrtava, 81000 Podgorica", 42.4349, 19.2657),
              VehicleConfigDto(17004, "Punkt wynajmu aut Centrum", "Odbiór samochodów w centrum miasta.", VehicleTypeDto.CAR, "Bulevar Svetog Petra Cetinjskog, 81000 Podgorica", 42.4426, 19.2621)
            )
          ),
          CityConfigDto(
            cityId = 1702,
            cityName = "Kotor",
            vehiclesConfig = listOf(
              VehicleConfigDto(17005, "Port Lotniczy Tivat (TIV)", "Najbliższe lotnisko, obsługujące Zatokę Kotorską.", VehicleTypeDto.PLANE, "Mrčevac, Tivat 85320", 42.4047, 18.7233),
              VehicleConfigDto(17006, "Brak bezpośrednich połączeń", "Kotor nie posiada stacji kolejowej.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(17007, "Autobuska stanica Kotor", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Put I Bokeljske Brigade, Kotor 85330", 42.4208, 18.7709),
              VehicleConfigDto(17008, "Punkt wynajmu aut Stare Miasto", "Odbiór samochodów przy murach Starego Miasta.", VehicleTypeDto.CAR, "Tabacina 1, Kotor 85330", 42.4262, 18.7712)
            )
          )
        )
      ),
      // === CZECHY ===
      CountryConfigDto(
        countryId = 18,
        continentId = 0,
        countryName = "Czechy",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1801,
            cityName = "Praga",
            vehiclesConfig = listOf(
              VehicleConfigDto(18001, "Port Lotniczy im. Václava Havla (PRG)", "Główne międzynarodowe lotnisko Czech.", VehicleTypeDto.PLANE, "Aviatická, 161 08 Praha 6", 50.1008, 14.2600),
              VehicleConfigDto(18002, "Praha hlavní nádraží", "Główny dworzec kolejowy Pragi.", VehicleTypeDto.TRAIN, "Wilsonova 300/8, 120 00 Vinohrady", 50.0831, 14.4352),
              VehicleConfigDto(18003, "ÚAN Florenc Praha", "Główny dworzec autobusowy dla tras krajowych i międzynarodowych.", VehicleTypeDto.BUS, "Křižíkova 2110/2b, 186 00 Karlín", 50.0903, 14.4421),
              VehicleConfigDto(18004, "Punkt wynajmu aut Staroměstské náměstí", "Odbiór aut w sercu Starego Miasta.", VehicleTypeDto.CAR, "Staroměstské nám. 1, 110 00 Staré Město", 50.0874, 14.4213)
            )
          ),
          CityConfigDto(
            cityId = 1802,
            cityName = "Brno",
            vehiclesConfig = listOf(
              VehicleConfigDto(18005, "Port Lotniczy Brno-Tuřany (BRQ)", "Międzynarodowe lotnisko obsługujące region Moraw.", VehicleTypeDto.PLANE, "Letiště Brno-Tuřany 627/1, 627 00 Brno-Tuřany", 49.1513, 16.6945),
              VehicleConfigDto(18006, "Brno hlavní nádraží", "Główny dworzec kolejowy w Brnie.", VehicleTypeDto.TRAIN, "Nádražní 418/1, 602 00 Brno", 49.1911, 16.6133),
              VehicleConfigDto(18007, "Autobusové nádraží u hotelu Grand", "Główny dworzec autobusowy w centrum.", VehicleTypeDto.BUS, "Benešova 22, 602 00 Brno", 49.1942, 16.6146),
              VehicleConfigDto(18008, "Punkt wynajmu aut Náměstí Svobody", "Odbiór samochodów na głównym placu Brna.", VehicleTypeDto.CAR, "Náměstí Svobody 9, 602 00 Brno", 49.1950, 16.6085)
            )
          )
        )
      ),
// === DANIA ===
      CountryConfigDto(
        countryId = 19,
        continentId = 0,
        countryName = "Dania",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 1901,
            cityName = "Kopenhaga",
            vehiclesConfig = listOf(
              VehicleConfigDto(19001, "Port Lotniczy Kopenhaga-Kastrup (CPH)", "Największe lotnisko w Skandynawii.", VehicleTypeDto.PLANE, "Lufthavnsboulevarden 6, 2770 Kastrup", 55.6180, 12.6560),
              VehicleConfigDto(19002, "Københavns Hovedbanegård", "Główny dworzec kolejowy Kopenhagi.", VehicleTypeDto.TRAIN, "Bernstorffsgade 16, 1577 København V", 55.6727, 12.5649),
              VehicleConfigDto(19003, "Dworzec autobusowy Ingerslevsgade", "Międzynarodowy terminal autobusowy przy dworcu głównym.", VehicleTypeDto.BUS, "Ingerslevsgade, 1704 København", 55.6698, 12.5630),
              VehicleConfigDto(19004, "Punkt wynajmu aut Nyhavn", "Odbiór samochodów w pobliżu malowniczego portu.", VehicleTypeDto.CAR, "Nyhavn 1, 1051 København K", 55.6793, 12.5902)
            )
          ),
          CityConfigDto(
            cityId = 1902,
            cityName = "Aarhus",
            vehiclesConfig = listOf(
              VehicleConfigDto(19005, "Port Lotniczy Aarhus (AAR)", "Lotnisko obsługujące drugi co do wielkości ośrodek miejski Danii.", VehicleTypeDto.PLANE, "Ny Lufthavnsvej 24, 8560 Kolind", 56.3000, 10.6190),
              VehicleConfigDto(19006, "Aarhus Hovedbanegård", "Główny dworzec kolejowy w Aarhus.", VehicleTypeDto.TRAIN, "Banegårdspladsen 1, 8000 Aarhus C", 56.1500, 10.2045),
              VehicleConfigDto(19007, "Aarhus Rutebilstation", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Fredensgade 45, 8000 Aarhus C", 56.1524, 10.2081),
              VehicleConfigDto(19008, "Punkt wynajmu aut ARoS", "Odbiór samochodów przy muzeum sztuki ARoS.", VehicleTypeDto.CAR, "Aros Allé 2, 8000 Aarhus C", 56.1541, 10.2000)
            )
          )
        )
      ),
// === ESTONIA ===
      CountryConfigDto(
        countryId = 20,
        continentId = 0,
        countryName = "Estonia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2001,
            cityName = "Tallinn",
            vehiclesConfig = listOf(
              VehicleConfigDto(20001, "Port Lotniczy Tallinn (TLL)", "Lotnisko im. Lennarta Meri, blisko centrum miasta.", VehicleTypeDto.PLANE, "Tartu maantee 101, 10112 Tallinn", 59.4133, 24.8328),
              VehicleConfigDto(20002, "Balti jaam", "Dworzec Bałtycki, główna stacja kolejowa Tallinna.", VehicleTypeDto.TRAIN, "Toompuiestee 37, 10149 Tallinn", 59.4417, 24.7369),
              VehicleConfigDto(20003, "Tallinna Bussijaam", "Główny dworzec autobusowy dla tras międzynarodowych.", VehicleTypeDto.BUS, "Lastekodu 46, 10144 Tallinn", 59.4269, 24.7761),
              VehicleConfigDto(20004, "Punkt wynajmu aut Vanalinn", "Odbiór aut na skraju Starego Miasta.", VehicleTypeDto.CAR, "Viru väljak 4, 10111 Tallinn", 59.4369, 24.7533)
            )
          ),
          CityConfigDto(
            cityId = 2002,
            cityName = "Tartu",
            vehiclesConfig = listOf(
              VehicleConfigDto(20005, "Port Lotniczy Tartu (TAY)", "Regionalne lotnisko obsługujące południową Estonię.", VehicleTypeDto.PLANE, "Lennu 40, 61709 Ülenurme", 58.3072, 26.6908),
              VehicleConfigDto(20006, "Tartu raudteejaam", "Historyczny dworzec kolejowy w Tartu.", VehicleTypeDto.TRAIN, "Vaksali 6, 50410 Tartu", 58.3736, 26.7058),
              VehicleConfigDto(20007, "Tartu Bussijaam", "Nowoczesny dworzec autobusowy w centrum miasta.", VehicleTypeDto.BUS, "Turu 2, 51014 Tartu", 58.3776, 26.7317),
              VehicleConfigDto(20008, "Punkt wynajmu aut Uniwersytet", "Odbiór samochodów w pobliżu Uniwersytetu w Tartu.", VehicleTypeDto.CAR, "Ülikooli 18, 50090 Tartu", 58.3807, 26.7222)
            )
          )
        )
      ),
// === FINLANDIA ===
      CountryConfigDto(
        countryId = 21,
        continentId = 0,
        countryName = "Finlandia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2101,
            cityName = "Helsinki",
            vehiclesConfig = listOf(
              VehicleConfigDto(21001, "Port Lotniczy Helsinki-Vantaa (HEL)", "Główne międzynarodowe lotnisko Finlandii.", VehicleTypeDto.PLANE, "Lentäjäntie 1, 01530 Vantaa", 60.3172, 24.9633),
              VehicleConfigDto(21002, "Helsingin päärautatieasema", "Centralny dworzec kolejowy w Helsinkach.", VehicleTypeDto.TRAIN, "Kaivokatu 1, 00100 Helsinki", 60.1719, 24.9414),
              VehicleConfigDto(21003, "Kampin linja-autoasema", "Podziemny dworzec autobusowy w kompleksie Kamppi.", VehicleTypeDto.BUS, "Urho Kekkosen katu 1, 00100 Helsinki", 60.1685, 24.9317),
              VehicleConfigDto(21004, "Punkt wynajmu aut Katedra", "Odbiór samochodów w pobliżu Placu Senackiego.", VehicleTypeDto.CAR, "Unioninkatu 29, 00170 Helsinki", 60.1700, 24.9526)
            )
          ),
          CityConfigDto(
            cityId = 2102,
            cityName = "Rovaniemi",
            vehiclesConfig = listOf(
              VehicleConfigDto(21005, "Port Lotniczy Rovaniemi (RVN)", "Oficjalne lotnisko Świętego Mikołaja.", VehicleTypeDto.PLANE, "Lentokentäntie, 96930 Rovaniemi", 66.5648, 25.8309),
              VehicleConfigDto(21006, "Rovaniemen rautatieasema", "Stacja kolejowa w stolicy Laponii.", VehicleTypeDto.TRAIN, "Ratakatu 3, 96100 Rovaniemi", 66.4958, 25.7225),
              VehicleConfigDto(21007, "Rovaniemen linja-autoasema", "Dworzec autobusowy w Rovaniemi.", VehicleTypeDto.BUS, "Lapinkävijäntie 2, 96200 Rovaniemi", 66.4981, 25.7247),
              VehicleConfigDto(21008, "Punkt wynajmu aut Santa's Village", "Odbiór aut w Wiosce Świętego Mikołaja.", VehicleTypeDto.CAR, "Tähtikuja 1, 96930 Rovaniemi", 66.5436, 25.8475)
            )
          )
        )
      ),
      // === GRECJA ===
      CountryConfigDto(
        countryId = 22,
        continentId = 0,
        countryName = "Grecja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2201,
            cityName = "Ateny",
            vehiclesConfig = listOf(
              VehicleConfigDto(22001, "Międzynarodowy Port Lotniczy Ateny (ATH)", "Lotnisko im. Elefteriosa Wenizelosa, główna brama Grecji.", VehicleTypeDto.PLANE, "Attiki Odos, Spata Artemida 190 04", 37.9364, 23.9445),
              VehicleConfigDto(22002, "Dworzec kolejowy Larissis", "Główny dworzec kolejowy w Atenach.", VehicleTypeDto.TRAIN, "Theodorou Diligianni, Athina 104 38", 37.9930, 23.7215),
              VehicleConfigDto(22003, "Dworzec autobusowy Kifissos (KTEL)", "Główny terminal dla autobusów dalekobieżnych.", VehicleTypeDto.BUS, "Leof. Kifisou 100, Athina 104 42", 37.9972, 23.7027),
              VehicleConfigDto(22004, "Punkt wynajmu aut Plac Syntagma", "Odbiór samochodów na centralnym placu Aten.", VehicleTypeDto.CAR, "Pl. Sintagmatos 4, Athina 105 63", 37.9752, 23.7342)
            )
          ),
          CityConfigDto(
            cityId = 2202,
            cityName = "Heraklion (Kreta)",
            vehiclesConfig = listOf(
              VehicleConfigDto(22005, "Port Lotniczy Heraklion (HER)", "Lotnisko im. Nikosa Kazantzakisa, główne lotnisko Krety.", VehicleTypeDto.PLANE, "Heraklion 716 01", 35.3397, 25.1803),
              VehicleConfigDto(22006, "Brak połączeń kolejowych", "Na Krecie nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(22007, "Centralny dworzec autobusowy KTEL", "Główny węzeł autobusowy dla całej wyspy.", VehicleTypeDto.BUS, "Leof. Nearchou, Iraklio 713 07", 35.3418, 25.1432),
              VehicleConfigDto(22008, "Punkt wynajmu aut Port Wenecki", "Odbiór aut w pobliżu historycznego portu.", VehicleTypeDto.CAR, "25is Avgoustou, Iraklio 712 02", 35.3424, 25.1357)
            )
          )
        )
      ),
// === GRUZJA ===
      CountryConfigDto(
        countryId = 23,
        continentId = 0,
        countryName = "Gruzja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2301,
            cityName = "Tbilisi",
            vehiclesConfig = listOf(
              VehicleConfigDto(23001, "Międzynarodowy Port Lotniczy Tbilisi (TBS)", "Główne lotnisko Gruzji.", VehicleTypeDto.PLANE, "Tbilisi, 0158", 41.6692, 44.9547),
              VehicleConfigDto(23002, "Centralny dworzec kolejowy w Tbilisi", "Główna stacja kolejowa stolicy.", VehicleTypeDto.TRAIN, "Station Square 2, Tbilisi", 41.7225, 44.7969),
              VehicleConfigDto(23003, "Dworzec autobusowy Ortachala", "Międzynarodowy dworzec autobusowy.", VehicleTypeDto.BUS, "Gulia St 1, Tbilisi", 41.6750, 44.8322),
              VehicleConfigDto(23004, "Punkt wynajmu aut Plac Wolności", "Odbiór samochodów na Placu Wolności.", VehicleTypeDto.CAR, "Freedom Square 4, Tbilisi 0105", 41.6934, 44.8015)
            )
          ),
          CityConfigDto(
            cityId = 2302,
            cityName = "Kutaisi",
            vehiclesConfig = listOf(
              VehicleConfigDto(23005, "Międzynarodowy Port Lotniczy Kutaisi (KUT)", "Ważny węzeł dla tanich linii lotniczych.", VehicleTypeDto.PLANE, "Kopitnari", 42.1769, 42.4828),
              VehicleConfigDto(23006, "Dworzec kolejowy Kutaisi I", "Główna stacja kolejowa w Kutaisi.", VehicleTypeDto.TRAIN, "Tamar Mepe St, Kutaisi", 42.2743, 42.7118),
              VehicleConfigDto(23007, "Centralny dworzec autobusowy w Kutaisi", "Obsługuje połączenia do głównych miast Gruzji.", VehicleTypeDto.BUS, "Chavchavadze Ave 67, Kutaisi", 42.2577, 42.6865),
              VehicleConfigDto(23008, "Punkt wynajmu aut Fontanna Kolchidy", "Odbiór aut przy centralnej fontannie.", VehicleTypeDto.CAR, "Tsminda Nino St 1, Kutaisi", 42.2662, 42.7029)
            )
          )
        )
      ),
// === HOLANDIA ===
      CountryConfigDto(
        countryId = 24,
        continentId = 0,
        countryName = "Holandia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2401,
            cityName = "Amsterdam",
            vehiclesConfig = listOf(
              VehicleConfigDto(24001, "Port Lotniczy Amsterdam-Schiphol (AMS)", "Jeden z największych i najważniejszych portów lotniczych w Europie.", VehicleTypeDto.PLANE, "Evert van de Beekstraat 202, 1118 CP Schiphol", 52.3105, 4.7683),
              VehicleConfigDto(24002, "Amsterdam Centraal", "Główny dworzec kolejowy Amsterdamu.", VehicleTypeDto.TRAIN, "Stationsplein, 1012 AB Amsterdam", 52.3792, 4.9002),
              VehicleConfigDto(24003, "Dworzec autobusowy Amsterdam Sloterdijk", "Główny hub dla międzynarodowych autobusów.", VehicleTypeDto.BUS, "Piarcoplein 1, 1043 DW Amsterdam", 52.3888, 4.8390),
              VehicleConfigDto(24004, "Punkt wynajmu aut Dworzec Główny", "Odbiór samochodów w pobliżu głównej stacji.", VehicleTypeDto.CAR, "Oosterdokskade 150, 1011 DK Amsterdam", 52.3768, 4.9036)
            )
          ),
          CityConfigDto(
            cityId = 2402,
            cityName = "Rotterdam",
            vehiclesConfig = listOf(
              VehicleConfigDto(24005, "Port Lotniczy Rotterdam-Haga (RTM)", "Lotnisko obsługujące region Rotterdamu i Hagi.", VehicleTypeDto.PLANE, "Rotterdam Airportplein 60, 3045 AP Rotterdam", 51.9569, 4.4372),
              VehicleConfigDto(24006, "Rotterdam Centraal", "Nowoczesny dworzec kolejowy, węzeł komunikacyjny.", VehicleTypeDto.TRAIN, "Stationsplein 1, 3013 AJ Rotterdam", 51.9231, 4.4674),
              VehicleConfigDto(24007, "Międzynarodowy dworzec autobusowy", "Terminal autobusowy przy dworcu centralnym.", VehicleTypeDto.BUS, "Conradstraat, 3013 AP Rotterdam", 51.9248, 4.4682),
              VehicleConfigDto(24008, "Punkt wynajmu aut Markthal", "Odbiór aut w pobliżu słynnej hali targowej.", VehicleTypeDto.CAR, "Dominee Jan Scharpstraat 298, 3011 GZ Rotterdam", 51.9201, 4.4849)
            )
          )
        )
      ),
// === IRLANDIA ===
      CountryConfigDto(
        countryId = 25,
        continentId = 0,
        countryName = "Irlandia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2501,
            cityName = "Dublin",
            vehiclesConfig = listOf(
              VehicleConfigDto(25001, "Port Lotniczy Dublin (DUB)", "Główne międzynarodowe lotnisko Irlandii.", VehicleTypeDto.PLANE, "Dublin Airport, Dublin, K67 D2P2", 53.4264, -6.2499),
              VehicleConfigDto(25002, "Heuston Station", "Jeden z dwóch głównych dworców kolejowych Dublina.", VehicleTypeDto.TRAIN, "St. John's Road West, Dublin 8", 53.3461, -6.2939),
              VehicleConfigDto(25003, "Busáras – Central Bus Station", "Główny dworzec autobusowy w Dublinie.", VehicleTypeDto.BUS, "Store St, Dublin 1", 53.3510, -6.2520),
              VehicleConfigDto(25004, "Punkt wynajmu aut O'Connell Street", "Odbiór aut przy głównej arterii miasta.", VehicleTypeDto.CAR, "33 O'Connell Street Upper, Dublin 1", 53.3512, -6.2612)
            )
          ),
          CityConfigDto(
            cityId = 2502,
            cityName = "Galway",
            vehiclesConfig = listOf(
              VehicleConfigDto(25005, "Port Lotniczy Shannon (SNN)", "Najbliższe duże lotnisko międzynarodowe dla zachodniej Irlandii.", VehicleTypeDto.PLANE, "Shannon, Co. Clare, V14 E397", 52.6999, -8.9168),
              VehicleConfigDto(25006, "Galway (Ceannt) Station", "Główny dworzec kolejowy i autobusowy w Galway.", VehicleTypeDto.TRAIN, "Station Road, Galway", 53.2737, -9.0478),
              VehicleConfigDto(25007, "Galway Coach Station", "Nowoczesny dworzec autobusowy obok stacji kolejowej.", VehicleTypeDto.BUS, "Fairgreen Rd, Galway", 53.2743, -9.0458),
              VehicleConfigDto(25008, "Punkt wynajmu aut Eyre Square", "Odbiór samochodów w centralnym punkcie miasta.", VehicleTypeDto.CAR, "Eyre Square, Galway", 53.2742, -9.0494)
            )
          )
        )
      ),
      // === ISLANDIA ===
      CountryConfigDto(
        countryId = 26,
        continentId = 0,
        countryName = "Islandia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2601,
            cityName = "Reykjavík",
            vehiclesConfig = listOf(
              VehicleConfigDto(26001, "Międzynarodowy Port Lotniczy Keflavík (KEF)", "Główny port lotniczy Islandii, obsługujący stolicę.", VehicleTypeDto.PLANE, "Keflavíkurflugvöllur, 235 Keflavík", 63.9850, -22.6056),
              VehicleConfigDto(26002, "Brak połączeń kolejowych", "Na Islandii nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(26003, "Terminal autobusowy BSÍ", "Główny dworzec dla autobusów dalekobieżnych i transferów lotniskowych.", VehicleTypeDto.BUS, "Vatnsmýrarvegur 10, 101 Reykjavík", 64.1396, -21.9360),
              VehicleConfigDto(26004, "Punkt wynajmu aut Hallgrímskirkja", "Odbiór samochodów w pobliżu słynnego kościoła.", VehicleTypeDto.CAR, "Skólavörðustígur, 101 Reykjavík", 64.1417, -21.9266)
            )
          ),
          CityConfigDto(
            cityId = 2602,
            cityName = "Akureyri",
            vehiclesConfig = listOf(
              VehicleConfigDto(26005, "Port Lotniczy Akureyri (AEY)", "Główne lotnisko północnej Islandii.", VehicleTypeDto.PLANE, "Urðargil 15, 600 Akureyri", 65.6600, -18.0727),
              VehicleConfigDto(26006, "Brak połączeń kolejowych", "Na Islandii nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(26007, "Terminal autobusowy Akureyri", "Węzeł autobusowy dla regionu północnego.", VehicleTypeDto.BUS, "Hafnarstræti 82, 600 Akureyri", 65.6823, -18.0903),
              VehicleConfigDto(26008, "Punkt wynajmu aut Centrum", "Odbiór samochodów w 'Stolicy Północy'.", VehicleTypeDto.CAR, "Hafnarstræti 90, 600 Akureyri", 65.6835, -18.0924)
            )
          )
        )
      ),
// === KAZACHSTAN ===
      CountryConfigDto(
        countryId = 27,
        continentId = 0,
        countryName = "Kazachstan",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2701,
            cityName = "Ałmaty",
            vehiclesConfig = listOf(
              VehicleConfigDto(27001, "Międzynarodowy Port Lotniczy Ałmaty (ALA)", "Największe lotnisko w Kazachstanie.", VehicleTypeDto.PLANE, "Mailin St 2, Almaty", 43.3521, 77.0405),
              VehicleConfigDto(27002, "Dworzec kolejowy Ałmaty-2", "Główny, centralny dworzec kolejowy miasta.", VehicleTypeDto.TRAIN, "Abylai Khan Ave 1, Almaty", 43.2755, 76.9442),
              VehicleConfigDto(27003, "Dworzec autobusowy Sayran", "Główny dworzec autobusów dalekobieżnych.", VehicleTypeDto.BUS, "Tole Bi St 294, Almaty", 43.2381, 76.8491),
              VehicleConfigDto(27004, "Punkt wynajmu aut Dostyk Plaza", "Odbiór samochodów w pobliżu centrum handlowego.", VehicleTypeDto.CAR, "Dostyk Ave 111, Almaty", 43.2339, 76.9538)
            )
          ),
          CityConfigDto(
            cityId = 2702,
            cityName = "Astana",
            vehiclesConfig = listOf(
              VehicleConfigDto(27005, "Międzynarodowy Port Lotniczy Nursułtan Nazarbajew (NQZ)", "Lotnisko obsługujące stolicę Kazachstanu.", VehicleTypeDto.PLANE, "Kabanbay Batyr Ave 119, Astana", 51.0222, 71.4669),
              VehicleConfigDto(27006, "Dworzec kolejowy Nur-Sultan-Nurly Zhol", "Nowy, futurystyczny dworzec kolejowy Astany.", VehicleTypeDto.TRAIN, "A 82 St, Astana", 51.1303, 71.5031),
              VehicleConfigDto(27007, "Dworzec autobusowy Saparzhai", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Goethe St 1, Astana", 51.1738, 71.4093),
              VehicleConfigDto(27008, "Punkt wynajmu aut Bayterek", "Odbiór samochodów w pobliżu wieży Bayterek.", VehicleTypeDto.CAR, "Dostyq St 1, Astana", 51.1283, 71.4304)
            )
          )
        )
      ),
// === LIECHTENSTEIN ===
      CountryConfigDto(
        countryId = 28,
        continentId = 0,
        countryName = "Liechtenstein",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2801,
            cityName = "Vaduz",
            vehiclesConfig = listOf(
              VehicleConfigDto(28001, "Port Lotniczy Zurych (ZRH), Szwajcaria", "Najbliższe duże lotnisko międzynarodowe.", VehicleTypeDto.PLANE, "8058 Zürich-Flughafen, Szwajcaria", 47.4647, 8.5492),
              VehicleConfigDto(28002, "Dworzec Schaan-Vaduz", "Najbliższa stacja kolejowa dla stolicy.", VehicleTypeDto.TRAIN, "Im Riet, 9494 Schaan", 47.1706, 9.5100),
              VehicleConfigDto(28003, "Dworzec autobusowy Vaduz Post", "Główny węzeł komunikacji autobusowej w kraju.", VehicleTypeDto.BUS, "Äulestrasse 38, 9490 Vaduz", 47.1392, 9.5222),
              VehicleConfigDto(28004, "Punkt wynajmu aut Centrum", "Odbiór samochodów w centrum Vaduz.", VehicleTypeDto.CAR, "Städtle 14, 9490 Vaduz", 47.1415, 9.5229)
            )
          ),
          CityConfigDto(
            cityId = 2802,
            cityName = "Schaan",
            vehiclesConfig = listOf(
              VehicleConfigDto(28005, "Port Lotniczy St. Gallen-Altenrhein (ACH), Szwajcaria", "Małe, alternatywne lotnisko w pobliżu.", VehicleTypeDto.PLANE, "Flughafenstrasse 11, 9423 Thal, Szwajcaria", 47.4851, 9.5608),
              VehicleConfigDto(28006, "Dworzec kolejowy Schaan-Vaduz", "Jedyna stacja kolejowa w Liechtensteinie.", VehicleTypeDto.TRAIN, "Im Riet, 9494 Schaan", 47.1706, 9.5100),
              VehicleConfigDto(28007, "Dworzec autobusowy Schaan Bahnhof", "Główny przystanek autobusowy przy stacji kolejowej.", VehicleTypeDto.BUS, "Landstrasse 1, 9494 Schaan", 47.1659, 9.5089),
              VehicleConfigDto(28008, "Punkt wynajmu aut Stacja", "Odbiór samochodów w najludniejszym mieście kraju.", VehicleTypeDto.CAR, "Feldkircher Strasse, 9494 Schaan", 47.1666, 9.5101)
            )
          )
        )
      ),
// === LITWA ===
      CountryConfigDto(
        countryId = 29,
        continentId = 0,
        countryName = "Litwa",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 2901,
            cityName = "Wilno",
            vehiclesConfig = listOf(
              VehicleConfigDto(29001, "Port Lotniczy Wilno (VNO)", "Międzynarodowe lotnisko stolicy Litwy.", VehicleTypeDto.PLANE, "Rodūnios kl. 10A, 02189 Vilnius", 54.6341, 25.2858),
              VehicleConfigDto(29002, "Vilniaus geležinkelio stotis", "Główny dworzec kolejowy Wilna.", VehicleTypeDto.TRAIN, "Geležinkelio g. 16, 02100 Vilnius", 54.6700, 25.2847),
              VehicleConfigDto(29003, "Vilniaus autobusų stotis", "Główny dworzec autobusowy, obok kolejowego.", VehicleTypeDto.BUS, "Sodų g. 22, 03211 Vilnius", 54.6713, 25.2859),
              VehicleConfigDto(29004, "Punkt wynajmu aut Katedra", "Odbiór samochodów przy Placu Katedralnym.", VehicleTypeDto.CAR, "Šventaragio g. 1, 01143 Vilnius", 54.6858, 25.2878)
            )
          ),
          CityConfigDto(
            cityId = 2902,
            cityName = "Kowno",
            vehiclesConfig = listOf(
              VehicleConfigDto(29005, "Port Lotniczy Kowno (KUN)", "Węzeł dla tanich linii lotniczych, drugi co do wielkości na Litwie.", VehicleTypeDto.PLANE, "Karmėlava, 54460 Kauno r. sav.", 54.9639, 24.0848),
              VehicleConfigDto(29006, "Kauno geležinkelio stotis", "Główny dworzec kolejowy w Kownie.", VehicleTypeDto.TRAIN, "M. K. Čiurlionio g. 16, 44355 Kaunas", 54.8872, 23.9221),
              VehicleConfigDto(29007, "Kauno autobusų stotis", "Nowoczesny, zintegrowany dworzec autobusowy.", VehicleTypeDto.BUS, "Vytauto pr. 24, 44355 Kaunas", 54.8932, 23.9238),
              VehicleConfigDto(29008, "Punkt wynajmu aut Laisvės alėja", "Odbiór samochodów przy głównym deptaku Kowna.", VehicleTypeDto.CAR, "Laisvės al. 50, 44246 Kaunas", 54.8973, 23.9144)
            )
          )
        )
      ),
      // === LUKSEMBURG ===
      CountryConfigDto(
        countryId = 30,
        continentId = 0,
        countryName = "Luksemburg",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3001,
            cityName = "Luksemburg",
            vehiclesConfig = listOf(
              VehicleConfigDto(30001, "Port Lotniczy Luksemburg (LUX)", "Główne i jedyne lotnisko w kraju.", VehicleTypeDto.PLANE, "4, rue de Trèves, 2632 Findel", 49.6234, 6.2044),
              VehicleConfigDto(30002, "Gare de Luxembourg", "Główny dworzec kolejowy kraju. Transport publiczny jest darmowy.", VehicleTypeDto.TRAIN, "Place de la Gare, 1616 Luxembourg", 49.6001, 6.1343),
              VehicleConfigDto(30003, "Dworzec autobusowy Gare Centrale", "Główny węzeł komunikacji autobusowej przy dworcu kolejowym.", VehicleTypeDto.BUS, "Place de la Gare, 1616 Luxembourg", 49.5997, 6.1340),
              VehicleConfigDto(30004, "Punkt wynajmu aut Kirchberg", "Odbiór samochodów w dzielnicy biznesowej.", VehicleTypeDto.CAR, "Avenue J.F. Kennedy, 1855 Luxembourg", 49.6262, 6.1607)
            )
          ),
          CityConfigDto(
            cityId = 3002,
            cityName = "Esch-sur-Alzette",
            vehiclesConfig = listOf(
              VehicleConfigDto(30005, "Port Lotniczy Luksemburg (LUX)", "Jedyne lotnisko w kraju, obsługuje również południe.", VehicleTypeDto.PLANE, "4, rue de Trèves, 2632 Findel", 49.6234, 6.2044),
              VehicleConfigDto(30006, "Gare d'Esch-sur-Alzette", "Główny dworzec kolejowy drugiego co do wielkości miasta.", VehicleTypeDto.TRAIN, "Place de la Gare, 4010 Esch-sur-Alzette", 49.4939, 5.9818),
              VehicleConfigDto(30007, "Dworzec autobusowy Esch/Alzette Gare", "Główny węzeł autobusowy miasta.", VehicleTypeDto.BUS, "Place de la Gare, 4010 Esch-sur-Alzette", 49.4936, 5.9821),
              VehicleConfigDto(30008, "Punkt wynajmu aut Centrum", "Odbiór samochodów w centrum miasta.", VehicleTypeDto.CAR, "Rue de l'Alzette 50, 4010 Esch-sur-Alzette", 49.4950, 5.9810)
            )
          )
        )
      ),
// === ŁOTWA ===
      CountryConfigDto(
        countryId = 31,
        continentId = 0,
        countryName = "Łotwa",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3101,
            cityName = "Ryga",
            vehiclesConfig = listOf(
              VehicleConfigDto(31001, "Międzynarodowy Port Lotniczy Ryga (RIX)", "Największe lotnisko w krajach bałtyckich.", VehicleTypeDto.PLANE, "Pārlielupes iela 1, Mārupe, LV-1053", 56.9236, 23.9711),
              VehicleConfigDto(31002, "Rīgas Pasažieru stacija", "Główny dworzec kolejowy Rygi.", VehicleTypeDto.TRAIN, "Stacijas laukums 2, Rīga, LV-1050", 56.9463, 24.1228),
              VehicleConfigDto(31003, "Rīgas Starptautiskā autoosta", "Międzynarodowy dworzec autobusowy.", VehicleTypeDto.BUS, "Prāgas iela 1, Rīga, LV-1050", 56.9442, 24.1166),
              VehicleConfigDto(31004, "Punkt wynajmu aut Vecrīga", "Odbiór samochodów w sercu Starego Miasta.", VehicleTypeDto.CAR, "Kaļķu iela 1A, Rīga, LV-1050", 56.9489, 24.1065)
            )
          ),
          CityConfigDto(
            cityId = 3102,
            cityName = "Jurmała",
            vehiclesConfig = listOf(
              VehicleConfigDto(31005, "Międzynarodowy Port Lotniczy Ryga (RIX)", "Najbliższe lotnisko, obsługujące kurort Jurmała.", VehicleTypeDto.PLANE, "Pārlielupes iela 1, Mārupe, LV-1053", 56.9236, 23.9711),
              VehicleConfigDto(31006, "Stacja kolejowa Majori", "Centralna stacja w sercu kurortu Jurmała.", VehicleTypeDto.TRAIN, "Jomas iela, Jūrmala, LV-2015", 56.9734, 23.7937),
              VehicleConfigDto(31007, "Przystanek autobusowy Majori", "Główny przystanek autobusowy przy ulicy Jomas.", VehicleTypeDto.BUS, "Jomas iela 42, Jūrmala, LV-2015", 56.9729, 23.7925),
              VehicleConfigDto(31008, "Punkt wynajmu aut Plaża", "Odbiór samochodów w pobliżu plaży w Majori.", VehicleTypeDto.CAR, "Jūras iela 23/25, Jūrmala, LV-2015", 56.9758, 23.7963)
            )
          )
        )
      ),
// === MACEDONIA PÓŁNOCNA ===
      CountryConfigDto(
        countryId = 32,
        continentId = 0,
        countryName = "Macedonia Północna",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3201,
            cityName = "Skopje",
            vehiclesConfig = listOf(
              VehicleConfigDto(32001, "Międzynarodowy Port Lotniczy Skopje (SKP)", "Główne lotnisko kraju.", VehicleTypeDto.PLANE, "s. Petrovec, Skopje 1043", 41.9616, 21.6214),
              VehicleConfigDto(32002, "Železnička stanica Skopje", "Zintegrowany dworzec kolejowo-autobusowy.", VehicleTypeDto.TRAIN, "Bulevar K.J. Pitu, Skopje 1000", 41.9902, 21.4447),
              VehicleConfigDto(32003, "Avtobuska stanica Skopje", "Główny dworzec autobusowy, w tym samym budynku co kolejowy.", VehicleTypeDto.BUS, "Bulevar K.J. Pitu, Skopje 1000", 41.9905, 21.4452),
              VehicleConfigDto(32004, "Punkt wynajmu aut Plac Macedonia", "Odbiór aut na głównym placu stolicy.", VehicleTypeDto.CAR, "Macedonia Square, Skopje 1000", 41.9960, 21.4315)
            )
          ),
          CityConfigDto(
            cityId = 3202,
            cityName = "Ochryda",
            vehiclesConfig = listOf(
              VehicleConfigDto(32005, "Port Lotniczy Ochryda (OHD)", "Lotnisko im. św. Pawła Apostoła, brama do Jeziora Ochrydzkiego.", VehicleTypeDto.PLANE, "Ohrid 6000", 41.1795, 20.7423),
              VehicleConfigDto(32006, "Brak połączeń kolejowych", "Ochryda nie posiada stacji kolejowej.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(32007, "Avtobuska stanica Ohrid", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Jane Sandanski, Ohrid 6000", 41.1278, 20.8093),
              VehicleConfigDto(32008, "Punkt wynajmu aut Stare Miasto", "Odbiór samochodów w pobliżu historycznego centrum.", VehicleTypeDto.CAR, "Kuzman Josifovski Pitu, Ohrid 6000", 41.1147, 20.8011)
            )
          )
        )
      ),
// === MALTA ===
      CountryConfigDto(
        countryId = 33,
        continentId = 0,
        countryName = "Malta",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3301,
            cityName = "Valletta",
            vehiclesConfig = listOf(
              VehicleConfigDto(33001, "Międzynarodowy Port Lotniczy Malta (MLA)", "Jedyne lotnisko na wyspie, zlokalizowane w Luqa.", VehicleTypeDto.PLANE, "Luqa, LQA 4000", 35.8575, 14.4839),
              VehicleConfigDto(33002, "Brak połączeń kolejowych", "Na Malcie nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(33003, "Dworzec autobusowy Valletta", "Główny węzeł autobusowy całej wyspy, tuż przy bramie miasta.", VehicleTypeDto.BUS, "Vjal Nelson, Il-Belt Valletta", 35.8951, 14.5085),
              VehicleConfigDto(33004, "Punkt wynajmu aut Floriana", "Odbiór samochodów tuż za murami Valletty.", VehicleTypeDto.CAR, "St. Anne Street, Floriana", 35.8938, 14.5057)
            )
          ),
          CityConfigDto(
            cityId = 3302,
            cityName = "Sliema",
            vehiclesConfig = listOf(
              VehicleConfigDto(33005, "Międzynarodowy Port Lotniczy Malta (MLA)", "Lotnisko obsługujące całą wyspę, w tym Sliemę.", VehicleTypeDto.PLANE, "Luqa, LQA 4000", 35.8575, 14.4839),
              VehicleConfigDto(33006, "Brak połączeń kolejowych", "Na Malcie nie istnieje sieć kolejowa.", VehicleTypeDto.TRAIN, "Brak", 0.0, 0.0),
              VehicleConfigDto(33007, "Przystanek autobusowy Sliema Ferries", "Główny węzeł komunikacyjny w Sliemie, z połączeniem promowym do Valletty.", VehicleTypeDto.BUS, "The Strand, Sliema", 35.9103, 14.5042),
              VehicleConfigDto(33008, "Punkt wynajmu aut The Strand", "Odbiór samochodów przy głównej promenadzie.", VehicleTypeDto.CAR, "Tower Road, Sliema", 35.9161, 14.4988)
            )
          )
        )
      ),
      // === MOŁDAWIA ===
      CountryConfigDto(
        countryId = 34,
        continentId = 0,
        countryName = "Mołdawia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3401,
            cityName = "Kiszyniów",
            vehiclesConfig = listOf(
              VehicleConfigDto(34001, "Międzynarodowy Port Lotniczy Kiszyniów (KIV)", "Główne lotnisko Mołdawii.", VehicleTypeDto.PLANE, "Bulevardul Dacia 80/3, Chișinău 2026", 46.9278, 28.9308),
              VehicleConfigDto(34002, "Gara feroviară din Chișinău", "Główny dworzec kolejowy w Kiszyniowie.", VehicleTypeDto.TRAIN, "Aleea Gării 1, Chișinău", 47.0055, 28.8560),
              VehicleConfigDto(34003, "Gara Auto Nord", "Północny dworzec autobusowy, jeden z głównych w mieście.", VehicleTypeDto.BUS, "Calea Moșilor 2/1, Chișinău", 47.0425, 28.8524),
              VehicleConfigDto(34004, "Punkt wynajmu aut Park Katedralny", "Odbiór samochodów w centralnym punkcie miasta.", VehicleTypeDto.CAR, "Bulevardul Ștefan cel Mare și Sfînt, Chișinău", 47.0253, 28.8315)
            )
          ),
          CityConfigDto(
            cityId = 3402,
            cityName = "Bielce",
            vehiclesConfig = listOf(
              VehicleConfigDto(34005, "Międzynarodowy Port Lotniczy Bielce (BZY)", "Drugie co do wielkości lotnisko w kraju.", VehicleTypeDto.PLANE, "Corlăteni, 3100", 47.8386, 27.7781),
              VehicleConfigDto(34006, "Dworzec kolejowy Bielce-Slobozia", "Główna stacja kolejowa w Bielcach.", VehicleTypeDto.TRAIN, "Strada Feroviarilor, Bălți", 47.7711, 27.9152),
              VehicleConfigDto(34007, "Autogara Bălți", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Strada Ștefan cel Mare 2, Bălți", 47.7592, 27.9234),
              VehicleConfigDto(34008, "Punkt wynajmu aut Plac Vasile Alecsandri", "Odbiór samochodów na głównym placu.", VehicleTypeDto.CAR, "Piața Vasile Alecsandri, Bălți", 47.7584, 27.9259)
            )
          )
        )
      ),
// === MONAKO ===
      CountryConfigDto(
        countryId = 35,
        continentId = 0,
        countryName = "Monako",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3501,
            cityName = "Monte Carlo",
            vehiclesConfig = listOf(
              VehicleConfigDto(35001, "Port Lotniczy Nicea-Lazurowe Wybrzeże (NCE), Francja", "Główne lotnisko obsługujące Monako.", VehicleTypeDto.PLANE, "Rue Costes et Bellonte, 06206 Nice, Francja", 43.6653, 7.2150),
              VehicleConfigDto(35002, "Gare de Monaco-Monte-Carlo", "Podziemny dworzec kolejowy obsługujący całe księstwo.", VehicleTypeDto.TRAIN, "Place Sainte-Dévote, 98000 Monaco", 43.7384, 7.4197),
              VehicleConfigDto(35003, "Przystanek autobusowy Casino", "Centralny przystanek w dzielnicy Monte Carlo.", VehicleTypeDto.BUS, "Place du Casino, 98000 Monaco", 43.7396, 7.4274),
              VehicleConfigDto(35004, "Punkt wynajmu aut Casino Square", "Odbiór luksusowych aut przy słynnym kasynie.", VehicleTypeDto.CAR, "Place du Casino, 98000 Monaco", 43.7392, 7.4280)
            )
          ),
          CityConfigDto(
            cityId = 3502,
            cityName = "La Condamine (Port)",
            vehiclesConfig = listOf(
              VehicleConfigDto(35005, "Port Lotniczy Nicea-Lazurowe Wybrzeże (NCE), Francja", "Najbliższy port lotniczy.", VehicleTypeDto.PLANE, "Rue Costes et Bellonte, 06206 Nice, Francja", 43.6653, 7.2150),
              VehicleConfigDto(35006, "Gare de Monaco-Monte-Carlo", "Główny i jedyny dworzec kolejowy w Monako.", VehicleTypeDto.TRAIN, "Place Sainte-Dévote, 98000 Monaco", 43.7384, 7.4197),
              VehicleConfigDto(35007, "Przystanek autobusowy Port Hercule", "Przystanek autobusowy w dzielnicy portowej.", VehicleTypeDto.BUS, "Route de la Piscine, 98000 Monaco", 43.7340, 7.4217),
              VehicleConfigDto(35008, "Punkt wynajmu aut Port Hercule", "Odbiór samochodów w pobliżu portu jachtowego.", VehicleTypeDto.CAR, "Quai Antoine 1er, 98000 Monaco", 43.7325, 7.4200)
            )
          )
        )
      ),
// === NORWEGIA ===
      CountryConfigDto(
        countryId = 36,
        continentId = 0,
        countryName = "Norwegia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3601,
            cityName = "Oslo",
            vehiclesConfig = listOf(
              VehicleConfigDto(36001, "Port Lotniczy Oslo-Gardermoen (OSL)", "Główne międzynarodowe lotnisko Norwegii.", VehicleTypeDto.PLANE, "Edvard Munchs veg, 2061 Gardermoen", 60.1976, 11.1004),
              VehicleConfigDto(36002, "Oslo Sentralstasjon (Oslo S)", "Główny dworzec kolejowy w Oslo.", VehicleTypeDto.TRAIN, "Jernbanetorget 1, 0154 Oslo", 59.9113, 10.7504),
              VehicleConfigDto(36003, "Oslo Bussterminal", "Główny dworzec autobusowy, połączony z dworcem kolejowym.", VehicleTypeDto.BUS, "Schweigaards gate 12, 0185 Oslo", 59.9103, 10.7569),
              VehicleConfigDto(36004, "Punkt wynajmu aut Opera", "Odbiór samochodów w pobliżu Opery w Oslo.", VehicleTypeDto.CAR, "Kirsten Flagstads Plass 1, 0150 Oslo", 59.9073, 10.7534)
            )
          ),
          CityConfigDto(
            cityId = 3602,
            cityName = "Bergen",
            vehiclesConfig = listOf(
              VehicleConfigDto(36005, "Port Lotniczy Bergen-Flesland (BGO)", "Lotnisko obsługujące zachodnią Norwegię i region fiordów.", VehicleTypeDto.PLANE, "Flyplassvegen 555, 5258 Bergen", 60.2934, 5.2181),
              VehicleConfigDto(36006, "Bergen stasjon", "Stacja końcowa malowniczej linii kolejowej Bergensbanen.", VehicleTypeDto.TRAIN, "Strømgaten 4, 5015 Bergen", 60.3901, 5.3317),
              VehicleConfigDto(36007, "Bergen busstasjon", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Lungegårdskaien 1, 5015 Bergen", 60.3888, 5.3323),
              VehicleConfigDto(36008, "Punkt wynajmu aut Bryggen", "Odbiór aut w pobliżu historycznego nabrzeża Bryggen.", VehicleTypeDto.CAR, "Bryggen 1, 5003 Bergen", 60.3971, 5.3241)
            )
          )
        )
      ),
// === PORTUGALIA ===
      CountryConfigDto(
        countryId = 37,
        continentId = 0,
        countryName = "Portugalia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3701,
            cityName = "Lizbona",
            vehiclesConfig = listOf(
              VehicleConfigDto(37001, "Port Lotniczy Lizbona (LIS)", "Lotnisko im. Humberto Delgado.", VehicleTypeDto.PLANE, "Alameda das Comunidades Portuguesas, 1700-111 Lisboa", 38.7756, -9.1354),
              VehicleConfigDto(37002, "Gare do Oriente", "Nowoczesny dworzec kolejowy i główny węzeł komunikacyjny.", VehicleTypeDto.TRAIN, "Av. Dom João II, 1990-233 Lisboa", 38.7678, -9.0991),
              VehicleConfigDto(37003, "Terminal Rodoviário de Sete Rios", "Jeden z głównych dworców autobusów dalekobieżnych.", VehicleTypeDto.BUS, "R. Prof. Lima Basto 1, 1500-423 Lisboa", 38.7413, -9.1663),
              VehicleConfigDto(37004, "Punkt wynajmu aut Praça do Comércio", "Odbiór samochodów na historycznym placu.", VehicleTypeDto.CAR, "Praça do Comércio, 1100-148 Lisboa", 38.7075, -9.1364)
            )
          ),
          CityConfigDto(
            cityId = 3702,
            cityName = "Porto",
            vehiclesConfig = listOf(
              VehicleConfigDto(37005, "Port Lotniczy Porto (OPO)", "Lotnisko im. Francisco Sá Carneiro.", VehicleTypeDto.PLANE, "4470-558 Maia", 41.2481, -8.6814),
              VehicleConfigDto(37006, "Estação de Campanhã", "Główny dworzec kolejowy dla pociągów dalekobieżnych.", VehicleTypeDto.TRAIN, "Largo da Estação, 4300-173 Porto", 41.1495, -8.5847),
              VehicleConfigDto(37007, "Terminal Intermodal de Campanhã", "Nowoczesny terminal autobusowy przy dworcu kolejowym.", VehicleTypeDto.BUS, "R. de Bonjóia, 4300-085 Porto", 41.1510, -8.5830),
              VehicleConfigDto(37008, "Punkt wynajmu aut Ribeira", "Odbiór samochodów w historycznej dzielnicy Ribeira.", VehicleTypeDto.CAR, "Cais da Ribeira, 4050-511 Porto", 41.1408, -8.6112)
            )
          )
        )
      ),
// === ROSJA ===
      CountryConfigDto(
        countryId = 38,
        continentId = 0,
        countryName = "Rosja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3801,
            cityName = "Moskwa",
            vehiclesConfig = listOf(
              VehicleConfigDto(38001, "Międzynarodowy Port Lotniczy Szeremietiewo (SVO)", "Jeden z głównych portów lotniczych Moskwy.", VehicleTypeDto.PLANE, "Khimki, Moscow Oblast, 141400", 55.9726, 37.4146),
              VehicleConfigDto(38002, "Dworzec Leningradzki", "Jeden z dziewięciu głównych dworców kolejowych stolicy.", VehicleTypeDto.TRAIN, "Komsomolskaya Square 3, Moscow, 107140", 55.7761, 37.6560),
              VehicleConfigDto(38003, "Centralny Dworzec Autobusowy (Shchelkovsky)", "Główny międzynarodowy dworzec autobusowy.", VehicleTypeDto.BUS, "Shchelkovskoye Hwy 75, Moscow, 107207", 55.8115, 37.8011),
              VehicleConfigDto(38004, "Punkt wynajmu aut Plac Czerwony", "Odbiór samochodów w historycznym sercu Rosji.", VehicleTypeDto.CAR, "Red Square, Moscow, 109012", 55.7540, 37.6204)
            )
          ),
          CityConfigDto(
            cityId = 3802,
            cityName = "Sankt Petersburg",
            vehiclesConfig = listOf(
              VehicleConfigDto(38005, "Port Lotniczy Pułkowo (LED)", "Główne lotnisko obsługujące Sankt Petersburg.", VehicleTypeDto.PLANE, "Pulkovskoye Shosse 41, St. Petersburg, 196140", 59.8003, 30.2625),
              VehicleConfigDto(38006, "Dworzec Moskiewski", "Główny dworzec kolejowy, obsługuje połączenia z Moskwą.", VehicleTypeDto.TRAIN, "Nevsky Prospect 85, St. Petersburg, 191036", 59.9309, 30.3621),
              VehicleConfigDto(38007, "Dworzec Autobusowy nr 2", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Naberezhnaya Obvodnogo Kanala 36, St. Petersburg, 192007", 59.9142, 30.3479),
              VehicleConfigDto(38008, "Punkt wynajmu aut Ermitaż", "Odbiór samochodów w pobliżu Pałacu Zimowego.", VehicleTypeDto.CAR, "Palace Square 2, St. Petersburg, 190000", 59.9390, 30.3158)
            )
          )
        )
      ),
// === RUMUNIA ===
      CountryConfigDto(
        countryId = 39,
        continentId = 0,
        countryName = "Rumunia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 3901,
            cityName = "Bukareszt",
            vehiclesConfig = listOf(
              VehicleConfigDto(39001, "Międzynarodowy Port Lotniczy Henri Coandă (OTP)", "Główne lotnisko Rumunii.", VehicleTypeDto.PLANE, "Calea Bucureștilor 224E, Otopeni 075150", 44.5711, 26.0850),
              VehicleConfigDto(39002, "Gara de Nord", "Główny dworzec kolejowy w Bukareszcie.", VehicleTypeDto.TRAIN, "Piața Gării de Nord 1, București", 44.4475, 26.0747),
              VehicleConfigDto(39003, "Autogara Militari", "Jeden z głównych dworców autobusowych stolicy.", VehicleTypeDto.BUS, "Strada Valea Cascadelor 1, București", 44.4334, 26.0125),
              VehicleConfigDto(39004, "Punkt wynajmu aut Pałac Parlamentu", "Odbiór aut przy jednym z największych budynków świata.", VehicleTypeDto.CAR, "Strada Izvor 2-4, București", 44.4275, 26.0872)
            )
          ),
          CityConfigDto(
            cityId = 3902,
            cityName = "Kluż-Napoka",
            vehiclesConfig = listOf(
              VehicleConfigDto(39005, "Międzynarodowy Port Lotniczy Avram Iancu (CLJ)", "Lotnisko obsługujące region Transylwanii.", VehicleTypeDto.PLANE, "Strada Traian Vuia 149, Cluj-Napoca 400397", 46.7853, 23.6862),
              VehicleConfigDto(39006, "Gara Cluj-Napoca", "Główny dworzec kolejowy w mieście.", VehicleTypeDto.TRAIN, "Piața Gării 1-3, Cluj-Napoca 400000", 46.7816, 23.5802),
              VehicleConfigDto(39007, "Autogara Fany", "Główny dworzec autobusowy w Klużu.", VehicleTypeDto.BUS, "Strada Ștefan cel Mare, Cluj-Napoca", 46.7770, 23.6062),
              VehicleConfigDto(39008, "Punkt wynajmu aut Piața Unirii", "Odbiór samochodów na centralnym placu miasta.", VehicleTypeDto.CAR, "Piața Unirii 1, Cluj-Napoca 400098", 46.7698, 23.5894)
            )
          )
        )
      ),
// === SAN MARINO ===
      CountryConfigDto(
        countryId = 40,
        continentId = 0,
        countryName = "San Marino",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4001,
            cityName = "San Marino (miasto)",
            vehiclesConfig = listOf(
              VehicleConfigDto(40001, "Port Lotniczy Rimini (RMI), Włochy", "Najbliższe międzynarodowe lotnisko.", VehicleTypeDto.PLANE, "Via Flaminia, 409, 47924 Rimini RN, Włochy", 44.0194, 12.6092),
              VehicleConfigDto(40002, "Stacja kolejowa Rimini, Włochy", "Najbliższy duży węzeł kolejowy.", VehicleTypeDto.TRAIN, "Piazzale Cesare Battisti, 1, 47921 Rimini RN, Włochy", 44.0664, 12.5786),
              VehicleConfigDto(40003, "Terminal autobusowy P1", "Główny terminal autobusowy i przystanek kolejki linowej.", VehicleTypeDto.BUS, "Piazzale Marino Giangi, San Marino", 43.9351, 12.4452),
              VehicleConfigDto(40004, "Punkt wynajmu aut Parking P9", "Odbiór samochodów na głównym parkingu.", VehicleTypeDto.CAR, "Via Piana, San Marino", 43.9340, 12.4475)
            )
          ),
          CityConfigDto(
            cityId = 4002,
            cityName = "Dogana",
            vehiclesConfig = listOf(
              VehicleConfigDto(40005, "Port Lotniczy Rimini (RMI), Włochy", "Lotnisko obsługujące cały region, w tym San Marino.", VehicleTypeDto.PLANE, "Via Flaminia, 409, 47924 Rimini RN, Włochy", 44.0194, 12.6092),
              VehicleConfigDto(40006, "Stacja kolejowa Rimini, Włochy", "Najbliższa stacja kolejowa.", VehicleTypeDto.TRAIN, "Piazzale Cesare Battisti, 1, 47921 Rimini RN, Włochy", 44.0664, 12.5786),
              VehicleConfigDto(40007, "Przystanek autobusowy Dogana", "Główny przystanek w największym mieście San Marino.", VehicleTypeDto.BUS, "Via III Settembre, Dogana", 43.9482, 12.4831),
              VehicleConfigDto(40008, "Punkt wynajmu aut Piazza del Mercato", "Odbiór samochodów w centrum handlowym kraju.", VehicleTypeDto.CAR, "Piazza del Mercato, Dogana", 43.9515, 12.4842)
            )
          )
        )
      ),
// === SERBIA ===
      CountryConfigDto(
        countryId = 41,
        continentId = 0,
        countryName = "Serbia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4101,
            cityName = "Belgrad",
            vehiclesConfig = listOf(
              VehicleConfigDto(41001, "Port Lotniczy Belgrad (BEG)", "Lotnisko im. Nikoli Tesli.", VehicleTypeDto.PLANE, "Aerodrom Beograd 59, Beograd 11180", 44.8184, 20.3091),
              VehicleConfigDto(41002, "Beograd Centar (Prokop)", "Nowy, główny dworzec kolejowy Belgradu.", VehicleTypeDto.TRAIN, "Bulevar Franše d'Eperea, Beograd", 44.7963, 20.4539),
              VehicleConfigDto(41003, "Beogradska autobuska stanica (BAS)", "Główny dworzec autobusowy.", VehicleTypeDto.BUS, "Železnička 4, Beograd 11000", 44.8091, 20.4560),
              VehicleConfigDto(41004, "Punkt wynajmu aut Knez Mihailova", "Odbiór samochodów przy głównym deptaku Belgradu.", VehicleTypeDto.CAR, "Knez Mihailova 6, Beograd 11000", 44.8169, 20.4578)
            )
          ),
          CityConfigDto(
            cityId = 4102,
            cityName = "Nowy Sad",
            vehiclesConfig = listOf(
              VehicleConfigDto(41005, "Port Lotniczy Belgrad (BEG)", "Najbliższe duże lotnisko międzynarodowe.", VehicleTypeDto.PLANE, "Aerodrom Beograd 59, Beograd 11180", 44.8184, 20.3091),
              VehicleConfigDto(41006, "Železnička stanica Novi Sad", "Główny dworzec kolejowy Nowego Sadu.", VehicleTypeDto.TRAIN, "Jaše Tomića, Novi Sad 21000", 45.2652, 19.8290),
              VehicleConfigDto(41007, "Međunarodna autobuska stanica Novi Sad", "Międzynarodowy dworzec autobusowy.", VehicleTypeDto.BUS, "Bulevar Jaše Tomića 6, Novi Sad 21000", 45.2659, 19.8306),
              VehicleConfigDto(41008, "Punkt wynajmu aut Trg slobode", "Odbiór samochodów na Placu Wolności.", VehicleTypeDto.CAR, "Trg slobode 1, Novi Sad 21000", 45.2552, 19.8451)
            )
          )
        )
      ),
      // === SŁOWACJA ===
      CountryConfigDto(
        countryId = 42,
        continentId = 0,
        countryName = "Słowacja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4201,
            cityName = "Bratysława",
            vehiclesConfig = listOf(
              VehicleConfigDto(42001, "Port Lotniczy Bratysława (BTS)", "Lotnisko im. M. R. Štefánika.", VehicleTypeDto.PLANE, "Ivanská cesta, 820 01 Bratislava", 48.1700, 17.2128),
              VehicleConfigDto(42002, "Bratislava hlavná stanica", "Główny dworzec kolejowy Słowacji.", VehicleTypeDto.TRAIN, "Námestie Franza Liszta, 811 04 Bratislava", 48.1584, 17.1059),
              VehicleConfigDto(42003, "Autobusová stanica Mlynské Nivy", "Nowoczesny, zintegrowany dworzec autobusowy.", VehicleTypeDto.BUS, "Mlynské nivy 5, 821 09 Bratislava", 48.1465, 17.1287),
              VehicleConfigDto(42004, "Punkt wynajmu aut Staré Mesto", "Odbiór samochodów w historycznym centrum.", VehicleTypeDto.CAR, "Hurbanovo námestie 1, 811 03 Bratislava", 48.1460, 17.1077)
            )
          ),
          CityConfigDto(
            cityId = 4202,
            cityName = "Koszyce",
            vehiclesConfig = listOf(
              VehicleConfigDto(42005, "Międzynarodowy Port Lotniczy Koszyce (KSC)", "Główne lotnisko wschodniej Słowacji.", VehicleTypeDto.PLANE, "Letisko Košice, 041 75 Košice", 48.6631, 21.2411),
              VehicleConfigDto(42006, "Železničná stanica Košice", "Główny dworzec kolejowy w Koszycach.", VehicleTypeDto.TRAIN, "Staničné námestie 9, 040 01 Košice", 48.7153, 21.2662),
              VehicleConfigDto(42007, "Autobusová stanica Košice", "Dworzec autobusowy zintegrowany z kolejowym.", VehicleTypeDto.BUS, "Staničné námestie 9, 040 01 Košice", 48.7149, 21.2668),
              VehicleConfigDto(42008, "Punkt wynajmu aut Katedra", "Odbiór aut w pobliżu Katedry św. Elżbiety.", VehicleTypeDto.CAR, "Hlavná 25, 040 01 Košice", 48.7200, 21.2579)
            )
          )
        )
      ),
// === SŁOWENIA ===
      CountryConfigDto(
        countryId = 43,
        continentId = 0,
        countryName = "Słowenia",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4301,
            cityName = "Lublana",
            vehiclesConfig = listOf(
              VehicleConfigDto(43001, "Port Lotniczy Lublana (LJU)", "Lotnisko im. Jože Pučnika, główny port Słowenii.", VehicleTypeDto.PLANE, "Zgornji Brnik 130a, 4210 Brnik", 46.2236, 14.4576),
              VehicleConfigDto(43002, "Železniška postaja Ljubljana", "Główny dworzec kolejowy stolicy.", VehicleTypeDto.TRAIN, "Trg Osvobodilne fronte 7, 1000 Ljubljana", 46.0588, 14.5103),
              VehicleConfigDto(43003, "Avtobusna postaja Ljubljana", "Główny dworzec autobusowy, obok kolejowego.", VehicleTypeDto.BUS, "Trg Osvobodilne fronte 4, 1000 Ljubljana", 46.0581, 14.5106),
              VehicleConfigDto(43004, "Punkt wynajmu aut Tromostovje", "Odbiór samochodów w pobliżu Potrójnego Mostu.", VehicleTypeDto.CAR, "Prešernov trg 1, 1000 Ljubljana", 46.0514, 14.5059)
            )
          ),
          CityConfigDto(
            cityId = 4302,
            cityName = "Maribor",
            vehiclesConfig = listOf(
              VehicleConfigDto(43005, "Port Lotniczy Maribor (MBX)", "Lotnisko im. Edvarda Rusjana.", VehicleTypeDto.PLANE, "Letališka cesta 10, 2312 Hoče", 46.4789, 15.6864),
              VehicleConfigDto(43006, "Železniška postaja Maribor", "Główny dworzec kolejowy w Mariborze.", VehicleTypeDto.TRAIN, "Partizanska cesta 50, 2000 Maribor", 46.5623, 15.6534),
              VehicleConfigDto(43007, "Avtobusna postaja Maribor", "Dworzec autobusowy w pobliżu stacji kolejowej.", VehicleTypeDto.BUS, "Mlinska ulica 1, 2000 Maribor", 46.5617, 15.6521),
              VehicleConfigDto(43008, "Punkt wynajmu aut Glavni trg", "Odbiór samochodów na głównym rynku miasta.", VehicleTypeDto.CAR, "Glavni trg 1, 2000 Maribor", 46.5577, 15.6455)
            )
          )
        )
      ),
// === SZWAJCARIA ===
      CountryConfigDto(
        countryId = 44,
        continentId = 0,
        countryName = "Szwajcaria",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4401,
            cityName = "Zurych",
            vehiclesConfig = listOf(
              VehicleConfigDto(44001, "Port Lotniczy Zurych (ZRH)", "Największe lotnisko w Szwajcarii.", VehicleTypeDto.PLANE, "8058 Zürich-Flughafen", 47.4647, 8.5492),
              VehicleConfigDto(44002, "Zürich Hauptbahnhof (HB)", "Główny dworzec kolejowy, jeden z najruchliwszych w Europie.", VehicleTypeDto.TRAIN, "Bahnhofplatz, 8001 Zürich", 47.3779, 8.5403),
              VehicleConfigDto(44003, "Sihlquai Bus Station", "Główny dworzec autobusów międzynarodowych.", VehicleTypeDto.BUS, "Sihlquai, 8005 Zürich", 47.3808, 8.5385),
              VehicleConfigDto(44004, "Punkt wynajmu aut Hauptbahnhof", "Odbiór samochodów w kompleksie dworca głównego.", VehicleTypeDto.CAR, "Europaplatz 1, 8004 Zürich", 47.3780, 8.5373)
            )
          ),
          CityConfigDto(
            cityId = 4402,
            cityName = "Genewa",
            vehiclesConfig = listOf(
              VehicleConfigDto(44005, "Port Lotniczy Genewa (GVA)", "Międzynarodowe lotnisko na granicy z Francją.", VehicleTypeDto.PLANE, "Route de l'Aéroport 21, 1215 Le Grand-Saconnex", 46.2381, 6.1089),
              VehicleConfigDto(44006, "Gare de Genève-Cornavin", "Główny dworzec kolejowy Genewy.", VehicleTypeDto.TRAIN, "Place de Cornavin 7, 1201 Genève", 46.2104, 6.1425),
              VehicleConfigDto(44007, "Gare routière de Genève", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "Place Dorcière, 1201 Genève", 46.2101, 6.1481),
              VehicleConfigDto(44008, "Punkt wynajmu aut Jet d'Eau", "Odbiór samochodów w pobliżu słynnej fontanny.", VehicleTypeDto.CAR, "Quai Gustave-Ador, 1207 Genève", 46.2057, 6.1559)
            )
          )
        )
      ),
// === SZWECJA ===
      CountryConfigDto(
        countryId = 45,
        continentId = 0,
        countryName = "Szwecja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4501,
            cityName = "Sztokholm",
            vehiclesConfig = listOf(
              VehicleConfigDto(45001, "Port Lotniczy Sztokholm-Arlanda (ARN)", "Główne międzynarodowe lotnisko Szwecji.", VehicleTypeDto.PLANE, "190 45 Stockholm-Arlanda", 59.6519, 17.9186),
              VehicleConfigDto(45002, "Stockholms centralstation", "Główny dworzec kolejowy i węzeł komunikacyjny.", VehicleTypeDto.TRAIN, "Centralplan 15, 111 20 Stockholm", 59.3301, 18.0583),
              VehicleConfigDto(45003, "Cityterminalen", "Centralny terminal autobusowy, połączony z dworcem kolejowym.", VehicleTypeDto.BUS, "Klarabergsviadukten 72, 111 64 Stockholm", 59.3314, 18.0594),
              VehicleConfigDto(45004, "Punkt wynajmu aut Ratusz", "Odbiór samochodów w pobliżu Ratusza Miejskiego.", VehicleTypeDto.CAR, "Hantverkargatan 1, 111 52 Stockholm", 59.3275, 18.0545)
            )
          ),
          CityConfigDto(
            cityId = 4502,
            cityName = "Göteborg",
            vehiclesConfig = listOf(
              VehicleConfigDto(45005, "Port Lotniczy Göteborg-Landvetter (GOT)", "Drugie co do wielkości lotnisko w Szwecji.", VehicleTypeDto.PLANE, "438 80 Landvetter", 57.6628, 12.2798),
              VehicleConfigDto(45006, "Göteborgs centralstation", "Główny dworzec kolejowy w Göteborgu.", VehicleTypeDto.TRAIN, "Drottningtorget 5, 411 03 Göteborg", 57.7088, 11.9734),
              VehicleConfigDto(45007, "Nils Ericson Terminalen", "Główny terminal autobusowy, obok stacji kolejowej.", VehicleTypeDto.BUS, "Nils Ericsonsgatan, 411 03 Göteborg", 57.7095, 11.9723),
              VehicleConfigDto(45008, "Punkt wynajmu aut Avenyn", "Odbiór samochodów przy głównej alei miasta.", VehicleTypeDto.CAR, "Kungsportsavenyen 1, 411 36 Göteborg", 57.7020, 11.9759)
            )
          )
        )
      ),
      // === TURCJA ===
      CountryConfigDto(
        countryId = 46,
        continentId = 0,
        countryName = "Turcja",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4601,
            cityName = "Stambuł",
            vehiclesConfig = listOf(
              VehicleConfigDto(46001, "Port Lotniczy Stambuł (IST)", "Nowe, gigantyczne lotnisko, główny węzeł międzynarodowy.", VehicleTypeDto.PLANE, "Tayakadın, Terminal Caddesi No:1, 34277 Arnavutköy/İstanbul", 41.2753, 28.7519),
              VehicleConfigDto(46002, "Dworzec Sirkeci (Marmaray)", "Historyczny dworzec, obecnie część linii Marmaray łączącej Europę i Azję.", VehicleTypeDto.TRAIN, "Hocapaşa, 34110 Fatih/İstanbul", 41.0150, 28.9790),
              VehicleConfigDto(46003, "Büyük İstanbul Otogarı (Esenler)", "Jeden z największych dworców autobusowych w Europie.", VehicleTypeDto.BUS, "Altıntepsi, 34035 Bayrampaşa/İstanbul", 41.0408, 28.8953),
              VehicleConfigDto(46004, "Punkt wynajmu aut Sultanahmet", "Odbiór samochodów w sercu historycznego Stambułu.", VehicleTypeDto.CAR, "Sultan Ahmet, Atmeydanı Cd. No:7, 34122 Fatih/İstanbul", 41.0055, 28.9769)
            )
          ),
          CityConfigDto(
            cityId = 4602,
            cityName = "Ankara",
            vehiclesConfig = listOf(
              VehicleConfigDto(46005, "Port Lotniczy Ankara Esenboğa (ESB)", "Międzynarodowe lotnisko obsługujące stolicę Turcji.", VehicleTypeDto.PLANE, "Balıkhisar, Özal Bulvarı, Akyurt/Ankara", 40.1281, 32.9951),
              VehicleConfigDto(46006, "Ankara YHT Garı", "Nowoczesny dworzec dla pociągów wysokich prędkości.", VehicleTypeDto.TRAIN, "Doğanbey, Celal Bayar Blv. No:78, 06050 Altındağ/Ankara", 39.9348, 32.8465),
              VehicleConfigDto(46007, "AŞTİ - Ankara Intercity Bus Terminal", "Główny terminal autobusowy w Ankarze.", VehicleTypeDto.BUS, "Beştepeler, Mevlana Blv., 06520 Yenimahalle/Ankara", 39.9192, 32.8123),
              VehicleConfigDto(46008, "Punkt wynajmu aut Anıtkabir", "Odbiór samochodów w pobliżu Mauzoleum Atatürka.", VehicleTypeDto.CAR, "Yücetepe, Akdeniz Cd. No:31, 06570 Çankaya/Ankara", 39.9252, 32.8369)
            )
          )
        )
      ),
// === UKRAINA ===
      CountryConfigDto(
        countryId = 47,
        continentId = 0,
        countryName = "Ukraina",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4701,
            cityName = "Kijów",
            vehiclesConfig = listOf(
              VehicleConfigDto(47001, "Międzynarodowy Port Lotniczy Boryspol (KBP)", "Główny port lotniczy Ukrainy (infrastruktura referencyjna).", VehicleTypeDto.PLANE, "Boryspil', Kyiv Oblast, 08307", 50.3450, 30.8947),
              VehicleConfigDto(47002, "Dworzec Kijów Pasażerski", "Główny dworzec kolejowy stolicy.", VehicleTypeDto.TRAIN, "Vokzal'na Square 1, Kyiv, 03031", 50.4404, 30.4870),
              VehicleConfigDto(47003, "Centralny Dworzec Autobusowy", "Główny węzeł autobusów dalekobieżnych.", VehicleTypeDto.BUS, "Demiivska Square 3, Kyiv, 03039", 50.4101, 30.5204),
              VehicleConfigDto(47004, "Punkt wynajmu aut Majdan", "Odbiór samochodów na Placu Niepodległości.", VehicleTypeDto.CAR, "Maidan Nezalezhnosti, Kyiv", 50.4505, 30.5234)
            )
          ),
          CityConfigDto(
            cityId = 4702,
            cityName = "Lwów",
            vehiclesConfig = listOf(
              VehicleConfigDto(47005, "Międzynarodowy Port Lotniczy Lwów (LWO)", "Główne lotnisko zachodniej Ukrainy (infrastruktura referencyjna).", VehicleTypeDto.PLANE, "Liubinska St, 168, L'viv, L'vivs'ka oblast, 79000", 49.8125, 23.9561),
              VehicleConfigDto(47006, "Główny dworzec kolejowy we Lwowie", "Historyczny, główny dworzec miasta.", VehicleTypeDto.TRAIN, "Dvirtseva Square 1, L'viv, L'vivs'ka oblast, 79000", 49.8392, 23.9934),
              VehicleConfigDto(47007, "Dworzec autobusowy Stryjski", "Główny dworzec autobusów międzynarodowych.", VehicleTypeDto.BUS, "Stryis'ka St, 109, L'viv, L'vivs'ka oblast, 79000", 49.7997, 24.0125),
              VehicleConfigDto(47008, "Punkt wynajmu aut Rynek", "Odbiór samochodów w sercu lwowskiego Rynku.", VehicleTypeDto.CAR, "Rynok Square 1, L'viv, L'vivs'ka oblast, 79008", 49.8419, 24.0315)
            )
          )
        )
      ),
// === WATYKAN ===
      CountryConfigDto(
        countryId = 48,
        continentId = 0,
        countryName = "Watykan",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4801,
            cityName = "Watykan",
            vehiclesConfig = listOf(
              VehicleConfigDto(48001, "Port Lotniczy Rzym-Fiumicino (FCO), Włochy", "Główne lotnisko obsługujące Rzym i Watykan.", VehicleTypeDto.PLANE, "Via dell' Aeroporto di Fiumicino, 00054 Fiumicino RM, Włochy", 41.8003, 12.2389),
              VehicleConfigDto(48002, "Stacja kolejowa Roma San Pietro, Włochy", "Najbliższa stacja kolejowa, tuż za murami Watykanu.", VehicleTypeDto.TRAIN, "Piazza della Stazione di S. Pietro, 00165 Roma RM, Włochy", 41.9008, 12.4497),
              VehicleConfigDto(48003, "Przystanek autobusowy Piazza del Risorgimento", "Główny węzeł autobusowy Rzymu przy Watykanie.", VehicleTypeDto.BUS, "Piazza del Risorgimento, 00192 Roma RM, Włochy", 41.9064, 12.4578),
              VehicleConfigDto(48004, "Wjazd samochodem ściśle ograniczony", "Ruch kołowy na terenie Watykanu jest dostępny tylko dla upoważnionych.", VehicleTypeDto.CAR, "Brak", 0.0, 0.0)
            )
          )
        )
      ),
// === WĘGRY ===
      CountryConfigDto(
        countryId = 49,
        continentId = 0,
        countryName = "Węgry",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 4901,
            cityName = "Budapeszt",
            vehiclesConfig = listOf(
              VehicleConfigDto(49001, "Port Lotniczy Budapeszt (BUD)", "Lotnisko im. Ferenca Liszta.", VehicleTypeDto.PLANE, "Budapest, 1185", 47.4399, 19.2556),
              VehicleConfigDto(49002, "Dworzec Keleti (Wschodni)", "Jeden z głównych, historycznych dworców kolejowych.", VehicleTypeDto.TRAIN, "Kerepesi út 2-4, Budapest, 1087", 47.5005, 19.0842),
              VehicleConfigDto(49003, "Dworzec autobusowy Népliget", "Główny międzynarodowy dworzec autobusowy.", VehicleTypeDto.BUS, "Üllői út 131, Budapest, 1091", 47.4754, 19.0988),
              VehicleConfigDto(49004, "Punkt wynajmu aut Parlament", "Odbiór samochodów w pobliżu budynku Parlamentu.", VehicleTypeDto.CAR, "Kossuth Lajos tér 1-3, Budapest, 1055", 47.5071, 19.0461)
            )
          ),
          CityConfigDto(
            cityId = 4902,
            cityName = "Debreczyn",
            vehiclesConfig = listOf(
              VehicleConfigDto(49005, "Międzynarodowy Port Lotniczy Debreczyn (DEB)", "Drugie co do wielkości lotnisko na Węgrzech.", VehicleTypeDto.PLANE, "Miklós utca, Debrecen, 4030", 47.4886, 21.6154),
              VehicleConfigDto(49006, "Dworzec kolejowy w Debreczynie", "Główny dworzec miasta.", VehicleTypeDto.TRAIN, "Petőfi tér 12, Debrecen, 4024", 47.5222, 21.6267),
              VehicleConfigDto(49007, "Dworzec autobusowy w Debreczynie", "Główny terminal autobusowy.", VehicleTypeDto.BUS, "Külsővásártér 3, Debrecen, 4025", 47.5255, 21.6206),
              VehicleConfigDto(49008, "Punkt wynajmu aut Plac Kossutha", "Odbiór aut na głównym placu miasta.", VehicleTypeDto.CAR, "Piac utca, Debrecen, 4024", 47.5309, 21.6251)
            )
          )
        )
      ),
// === WIELKA BRYTANIA ===
      CountryConfigDto(
        countryId = 50,
        continentId = 0,
        countryName = "Wielka Brytania",
        citiesConfig = listOf(
          CityConfigDto(
            cityId = 5001,
            cityName = "Londyn",
            vehiclesConfig = listOf(
              VehicleConfigDto(50001, "Port Lotniczy Heathrow (LHR)", "Jeden z najbardziej ruchliwych portów lotniczych na świecie.", VehicleTypeDto.PLANE, "Longford, Hounslow, TW6", 51.4700, -0.4543),
              VehicleConfigDto(50002, "St Pancras International", "Dworzec końcowy dla pociągów Eurostar z kontynentu.", VehicleTypeDto.TRAIN, "Euston Rd, London N1C 4QP", 51.5308, -0.1257),
              VehicleConfigDto(50003, "Victoria Coach Station", "Główny dworzec autobusów dalekobieżnych w Londynie.", VehicleTypeDto.BUS, "164 Buckingham Palace Rd, London SW1W 9TP", 51.4925, -0.1480),
              VehicleConfigDto(50004, "Punkt wynajmu aut Hyde Park Corner", "Odbiór samochodów w centralnej lokalizacji.", VehicleTypeDto.CAR, "1 Knightsbridge, London SW1X 7LY", 51.5027, -0.1527)
            )
          ),
          CityConfigDto(
            cityId = 5002,
            cityName = "Edynburg",
            vehiclesConfig = listOf(
              VehicleConfigDto(50005, "Port Lotniczy Edynburg (EDI)", "Najbardziej ruchliwe lotnisko w Szkocji.", VehicleTypeDto.PLANE, "Edinburgh, EH12 9DN", 55.9508, -3.3725),
              VehicleConfigDto(50006, "Edinburgh Waverley", "Główny dworzec kolejowy Edynburga.", VehicleTypeDto.TRAIN, "Princes St, Edinburgh EH1 1BB", 55.9521, -3.1890),
              VehicleConfigDto(50007, "Edinburgh Bus Station", "Główny dworzec autobusowy miasta.", VehicleTypeDto.BUS, "26 Elder St, Edinburgh EH1 3DX", 55.9556, -3.1908),
              VehicleConfigDto(50008, "Punkt wynajmu aut Royal Mile", "Odbiór aut w sercu historycznego Edynburga.", VehicleTypeDto.CAR, "275 Canongate, Edinburgh EH8 8BQ", 55.9507, -3.1802)
            )
          )
        )
      )

    )
  )
}