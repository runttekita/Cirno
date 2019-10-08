#!/bin/bash
CODE=./src/main/kotlin/cirno/
RESOURCES=./src/main/resources/cirno/
PROD_STRINGS=${RESOURCES}localization/eng/prodStrings/
DEV_STRINGS=${RESOURCES}localization/eng/
GOD_OBJECT=${CODE}cirno.kt
CARDS=${CODE}cards/*
RELICS=${CODE}relics/*
BIG_CARD_BACK=${RESOURCES}images/1024/*
SMOL_CARD_BACK=${RESOURCES}images/512/*
BIG_CARD_BACK_PROD=${RESOURCES}images/1024prod/
SMOL_CARD_BACK_PROD=${RESOURCES}images/512prod/

function finish {
  sed -i '\/\/delete/d' ./src/main/kotlin/cirno/daten/ReceiveEditCards.kt
  sed -i '\/\/delete/d' ./src/main/kotlin/cirno/daten/ReceiveEditRelics.kt
  rm ${PROD_STRINGS}/[a-z]*
  rm ${BIG_CARD_BACK_PROD}/[a-z]*
  rm ${SMOL_CARD_BACK_PROD}/[a-z]*
  if [ "$2" == "-p" ]
  then
    (echo "${PATCH_CONTENTS}" > ${CODE}/patches/DONTLETTHISPATCHGETTOPRODUCTION.java)
  fi
}

if [ "$1" == "-f" ]
then
  finish
  exit 1
fi

# Idiot proof
if [ "$1" == "-p" ] || [ "$1" == "" ]
then
  echo PICK LANGUAGE TO COMPILE TO
  finish
  exit 1
fi

set -e
if [ "$1" == "--eng" ] || [ "$2" == "--eng" ]
then
  echo "Compiling English Jar"
  DAMAGE='Deal !D! damage.'
  BLOCK='Gain !B! Block.'
  DRAW='Draw !M! cards.'
  COLD='Apply !M! cirno:Cold.'
  FREEZE='cirno:Freeze an enemy.'
  ETHEREAL='Ethereal.'
  EXHAUST='Exhaust.'
  SPREAD_COLD='Apply !M! cirno:Cold to target and to adjacent enemies.'
  ICE_BARRIER='Whenever you are attacked this turn, apply cirno:Cold back.'
  NL='NL'
  DAMAGE_AOE='Deal !D! damage to ALL enemies.'
  AOE_COLD='Apply !M! cirno:Cold to ALL enemies.'
  MAGIC_BLOCK='Gain !M! Block.'
  CONDITIONAL_COLD='If the enemy is cirno:Cold, apply !M! cirno:Cold.'
  COLD_DRAW='Skill, apply 1 cirno:Cold.'
  RANDOM_COLD='Apply 1 cirno:Cold to a random enemy for each card discarded.'
  RETAIN='Retain.'
  MAGIC_DAMAGE='deal !M! damage.'
  SPELL='cirno:Spell'
  AND='and'
  SPELL_FREEZE='Freeze the attacker.'
  WHEN_ATTACKED='When you are attacked, '
fi

# Copy into production folder
cp ${DEV_STRINGS}card.json ${PROD_STRINGS}card.json
cp ${DEV_STRINGS}powers.json ${PROD_STRINGS}powers.json
cp ${DEV_STRINGS}ui.json ${PROD_STRINGS}ui.json
cp ${DEV_STRINGS}relics.json ${PROD_STRINGS}relics.json
cp ${DEV_STRINGS}keywords.json ${PROD_STRINGS}keywords.json
cp ${DEV_STRINGS}stances.json ${PROD_STRINGS}stances.json

# Replace strings
PROD_JSON=${PROD_STRINGS}card.json
sed -i s/\$whenattacked/"${WHEN_ATTACKED}"/g ${PROD_JSON}
sed -i s/\$spellfreeze/"${SPELL_FREEZE}"/g ${PROD_JSON}
sed -i s/\$spell/"${SPELL}"/g ${PROD_JSON}
sed -i s/\$magicdamage/"${MAGIC_DAMAGE}"/g ${PROD_JSON}
sed -i s/\$retain/"${RETAIN}"/g ${PROD_JSON}
sed -i s/\$randomcold/"${RANDOM_COLD}"/g ${PROD_JSON}
sed -i s/\$colddraw/"${COLD_DRAW}"/g ${PROD_JSON}
sed -i s/\$damage/"${DAMAGE}"/g ${PROD_JSON}
sed -i s/\$magicblock/"${MAGIC_BLOCK}"/g ${PROD_JSON}
sed -i s/\$block/"${BLOCK}"/g ${PROD_JSON}
sed -i s/\$cold/"${COLD}"/g ${PROD_JSON}
sed -i s/\$freeze/"${FREEZE}"/g ${PROD_JSON}
sed -i s/\$ethereal/"${ETHEREAL}"/g ${PROD_JSON}
sed -i s/\$exhaust/"${EXHAUST}"/g ${PROD_JSON}
sed -i s/\$spreadcold/"${SPREAD_COLD}"/g ${PROD_JSON}
sed -i s/\$icebarrier/"${ICE_BARRIER}"/g ${PROD_JSON}
sed -i s/\$nl/"${NL}"/g ${PROD_JSON}
sed -i s/\$multidmg/"${DAMAGE_AOE}"/g ${PROD_JSON}
sed -i s/\$aoecold/"${AOE_COLD}"/g ${PROD_JSON}
sed -i s/\$conditionalcold/"${CONDITIONAL_COLD}"/g ${PROD_JSON}
sed -i s/\$draw/"${DRAW}"/g ${PROD_JSON}
sed -i s/\$and/"${AND}"/g ${PROD_JSON}
sed -i s/'[[:alnum:]]*": {'/'Cirno:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}powers.json
sed -i s/'[[:alnum:]]*": {'/'Cirno:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}ui.json
sed -i s/'[[:alnum:]]*": {'/'Cirno:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}relics.json
sed -i s/'[[:alnum:]]*": {'/'Cirno:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}stances.json
sed -i s/'[[:alnum:]]*": {'/'Cirno:&'/g ${PROD_JSON}

# Autoadd Cards
for f in ${CARDS}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev | cut -d"." -f1)
    sed -i "s|\/\/autoAddCards|BaseMod.addCard(${ADD}());\/\/delete\n\t\t\/\/autoAddCards|g" ./src/main/kotlin/cirno/daten/ReceiveEditCards.kt
done

# Autoadd Relics
for f in ${RELICS}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev | cut -d"." -f1)
    sed -i "s|\/\/autoAddRelics|BaseMod.addRelicToCustomPool(${ADD}(), CirnoChar.Enums.enums.Cirno_Ice);\/\/delete\n\t\t\/\/autoAddRelics|g" ./src/main/kotlin/cirno/daten/ReceiveEditRelics.kt
done

# images!
for f in ${BIG_CARD_BACK}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev )
    convert ${f} -fill blue -tint 50 ${RESOURCES}images/1024prod/${ADD}
done

# images!
for f in ${SMOL_CARD_BACK}
do
    ADD=$(echo $f | rev | cut -d"/" -f1 | rev )
    convert ${f} -fill blue -tint 50 ${RESOURCES}images/512prod/${ADD}
done

# Release mod
if [ "$2" == "-p" ]
then
  PATCH=${CODE}/patches/DONTLETTHISPATCHGETTOPRODUCTION.java
  PATCH_CONTENTS=$(cat ${PATCH})
  rm ${PATCH}
fi

# Release on steam
if [ "$2" == "-p" ]
then
  echo TODO release on steam
fi

# Dev Stuff
if [ "$2" != "-p" ]
then
  mvn package
  finish "$@"
  java -jar ../.local/share/Steam/steamapps/workshop/content/646570/1605060445/ModTheSpire.jar --skip-launcher
fi
