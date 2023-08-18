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

bsastunel = tunelR bsaslink2 [zarate, mercedes, victoria]




buenosAiresWithMercedes = foundR buenosAires mercedes

buenosAiresWithMZ = linkR buenosAiresWithMercedes mercedes zarate badQ

buenosAiresWithMV = linkR buenosAiresWithMZ mercedes victoria highQ 

buenosAiresWithTunnel = tunelR buenosAiresWithMV [zarate, mercedes, victoria]

testFoundR = foundR buenosAires mercedes == buenosAiresWithMercedes 

testLinkR = linkR buenosAiresWithMercedes mercedes zarate badQ == buenosAiresWithMZ

testTunelR = tunelR buenosAiresWithMV [zarate, mercedes, victoria] == buenosAiresWithTunnel

testConnectedR = connectedR buenosAiresWithTunnel mercedes zarate

testLinkedR = linkedR buenosAiresWithTunnel mercedes victoria 

testDelayR = delayR buenosAiresWithTunnel mercedes zarate

testCapacity = availableCapacityForR buenosAiresWithTunnel mercedes zarate

