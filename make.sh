#!/bin/bash
TARGET=./src/main/resources/Piruru/localization/eng/prodStrings/
SOURCE=./src/main/resources/Piruru/localization/eng/
GOD_OBJECT=./src/main/java/Piruru/Piruru.java
CARDS=./src/main/java/Piruru/cards/*
RELICS=./src/main/java/Piruru/relics/*
BIG_CARD_BACK=./src/main/resources/Piruru/images/1024/*
SMOL_CARD_BACK=./src/main/resources/Piruru/images/512/*
BIG_CARD_BACK_PROD=./src/main/resources/Piruru/images/1024prod/
SMOL_CARD_BACK_PROD=./src/main/resources/Piruru/images/512prod/

# LOCALIZERS CHANGE THIS
DAMAGE="Deal !D! Damage."
BLOCK="Gain !B! Block."
DISCARD_ONE="Discard 1 card."
ENERGY="Gain !M! [E] ."
DRAW="Draw !M! cards."
COLD="Apply !M! Piruru:Cold."
FREEZE="Piruru:Freeze an enemy."
MILL='Piruru:Mill !M! cards.'
ETHEREAL='Ethereal.'
EXHAUST='Exhaust.'
RECOVER='Put a card from your discard pile into your hand.'

# Copy into production folder
cp ${SOURCE}card.json ${TARGET}card.json
cp ${SOURCE}powers.json ${TARGET}powers.json
cp ${SOURCE}ui.json ${TARGET}ui.json
cp ${SOURCE}pirurelic.json ${TARGET}pirurelic.json

# Replace strings
PROD_JSON=${TARGET}card.json
sed -i s/\$damage/"${DAMAGE}"/g ${PROD_JSON}
sed -i s/\$block/"${BLOCK}"/g ${PROD_JSON}
sed -i s/\$discardOne/"${DISCARD_ONE}"/g ${PROD_JSON}
sed -i s/\$energy/"${ENERGY}"/g ${PROD_JSON}
sed -i s/\$draw/"${DRAW}"/g ${PROD_JSON}
sed -i s/\$cold/"${COLD}"/g ${PROD_JSON}
sed -i s/\$freeze/"${FREEZE}"/g ${PROD_JSON}
sed -i s/\$mill/"${MILL}"/g ${PROD_JSON}
sed -i s/\$ethereal/"${ETHEREAL}"/g ${PROD_JSON}
sed -i s/\$exhaust/"${EXHAUST}"/g ${PROD_JSON}
sed -i s/\$recover/"${RECOVER}"/g ${PROD_JSON}
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${TARGET}powers.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${TARGET}ui.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${TARGET}pirurelic.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

# Autoadd Cards
for f in ${CARDS}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev | cut -d"." -f1)
    sed -i "s|\/\/autoAddCards|BaseMod.addCard(new ${ADD}());\/\/delete\n\t\t\/\/autoAddCards|g" ${GOD_OBJECT}
done

# Autoadd Relics
for f in ${RELICS}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev | cut -d"." -f1)
    sed -i "s|\/\/autoAddRelics|BaseMod.addRelicToCustomPool(new ${ADD}(), PiruruChar.Enums.PIRURU_ICE);\/\/delete\n\t\t\/\/autoAddRelics|g" ${GOD_OBJECT}
done

#images!
for f in ${BIG_CARD_BACK}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev )
    convert ${f} -fill blue -tint 50 ./src/main/resources/Piruru/images/1024prod/${ADD}
done

#images!
for f in ${SMOL_CARD_BACK}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev )
    convert ${f} -fill blue -tint 50 ./src/main/resources/Piruru/images/512prod/${ADD}
done

#finally do the thing
mvn package

#clean up after myself
sed -i '\/\/delete/d' ${GOD_OBJECT}
rm ${TARGET}/[a-z]*
rm ${BIG_CARD_BACK_PROD}/[a-z]*
rm ${SMOL_CARD_BACK_PROD}/[a-z]*