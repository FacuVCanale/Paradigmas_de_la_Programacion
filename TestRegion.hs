import Point
import City
import Quality
import Link
import Tunel

import Region

import TestCity
import TestQuality
import TestLink
import TestTunel

buenosAires = newR

buenosAiress = foundR buenosAires mercedes

bsas = foundR buenosAiress zarate

bsas1 = foundR bsas victoria

bsaslink1 = linkR bsas1 mercedes zarate badQ

bsaslink2 = linkR bsaslink1 mercedes victoria highQ


bsastunel = tunelR bsaslink2 [zarate, victoria]


test = tunelR bsaslink1 [zarate, mercedes] -- ANDAAAAAAA

