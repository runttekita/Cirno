#!/bin/bash
CODE=./src/main/java/Piruru/
RESOURCES=./src/main/resources/Piruru/
PROD_STRINGS=${RESOURCES}localization/eng/prodStrings/
DEV_STRINGS=${RESOURCES}localization/eng/
GOD_OBJECT=${CODE}Piruru.java
CARDS=${CODE}cards/*
RELICS=${CODE}relics/*
BIG_CARD_BACK=${RESOURCES}images/1024/*
SMOL_CARD_BACK=${RESOURCES}images/512/*
BIG_CARD_BACK_PROD=${RESOURCES}images/1024prod/
SMOL_CARD_BACK_PROD=${RESOURCES}images/512prod/
PATCH=""
PATCH_CONTENTS=""
DAMAGE=''
BLOCK=''
DISCARD_ONE=''
ENERGY=''
DRAW=''
COLD=''
FREEZE=''
MILL=''
ETHEREAL=''
EXHAUST=''
RECOVER=''
RECOVER_SKILLS=''
RECOVER_SKILLS_IGNORE_HAND=''
SPREAD_COLD=''
ICE_BARRIER=''
INFINITE_UPGRADES=''
SCOUT_ATTACKS=''
INFINITE_ENERGY_DISCARD=''
NL=''

function finish {
  sed -i '\/\/delete/d' ${GOD_OBJECT}
  rm ${PROD_STRINGS}/[a-z]*
  rm ${BIG_CARD_BACK_PROD}/[a-z]*
  rm ${SMOL_CARD_BACK_PROD}/[a-z]*
  if [ "$2" == "-p" ]
  then
    (echo "${PATCH_CONTENTS}" > ${CODE}/patches/DONTLETTHISPATCHGETTOPRODUCTION.java)
  fi
}

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
  DAMAGE='Deal !D! Damage.'
  BLOCK='Gain !B! Block.'
  DISCARD_ONE='Discard 1 card.'
  ENERGY='Gain !M! [E] .'
  DRAW='Draw !M! cards.'
  COLD='Apply !M! piruru:Cold.'
  FREEZE='piruru:Freeze an enemy.'
  MILL='piruru:Mill !M! cards.'
  ETHEREAL='Ethereal.'
  EXHAUST='Exhaust.'
  RECOVER='Put a card from your discard pile into your hand.'
  RECOVER_SKILLS='Put all Skills from your discard pile into your hand.'
  RECOVER_SKILLS_IGNORE_HAND='Put all Skills from your discard pile into your hand ignoring max hand size.'
  SPREAD_COLD='Apply !M! piruru:Cold to target and to adjacent enemies.'
  ICE_BARRIER='Whenever you are attacked this turn, apply piruru:Cold back.'
  INFINITE_UPGRADES='Can be Upgraded any number of times.'
  SCOUT_ATTACKS='Discard all cards drawn this way that are not Attacks.'
  INFINITE_ENERGY_DISCARD='Discard any amount of cards and gain the same amount of [E] as discarded cards.'
  NL='NL'
fi

if [ "$1" == "--zhs" ] || [ "$2" == "--zhs" ]
then
  echo "Compiling ZHS Jar"
  DAMAGE=''
  BLOCK=''
  DISCARD_ONE=''
  ENERGY=''
  DRAW=''
  COLD=''
  FREEZE=''
  MILL=''
  ETHEREAL=''
  EXHAUST=''
  RECOVER=''
  RECOVER_SKILLS=''
  RECOVER_SKILLS_IGNORE_HAND=''
  SPREAD_COLD=''
  ICE_BARRIER=''
  INFINITE_UPGRADES=''
  SCOUT_ATTACKS=''
  INFINITE_ENERGY_DISCARD=''
  NL=''
fi

if [ "$1" == "--zht" ] || [ "$2" == "--zht" ]
then
  echo "Compiling ZHT Jar"
  DAMAGE=''
  BLOCK=''
  DISCARD_ONE=''
  ENERGY=''
  DRAW=''
  COLD=''
  FREEZE=''
  MILL=''
  ETHEREAL=''
  EXHAUST=''
  RECOVER=''
  RECOVER_SKILLS=''
  RECOVER_SKILLS_IGNORE_HAND=''
  SPREAD_COLD=''
  ICE_BARRIER=''
  INFINITE_UPGRADES=''
  SCOUT_ATTACKS=''
  INFINITE_ENERGY_DISCARD=''
  NL=''
fi


if [ "$1" == "--jpn" ] || [ "$2" == "--jpn" ]
then
  echo "Compiling JPN Jar"
  DAMAGE=''
  BLOCK=''
  DISCARD_ONE=''
  ENERGY=''
  DRAW=''
  COLD=''
  FREEZE=''
  MILL=''
  ETHEREAL=''
  EXHAUST=''
  RECOVER=''
  RECOVER_SKILLS=''
  RECOVER_SKILLS_IGNORE_HAND=''
  SPREAD_COLD=''
  ICE_BARRIER=''
  INFINITE_UPGRADES=''
  SCOUT_ATTACKS=''
  INFINITE_ENERGY_DISCARD=''
  NL=''
fi

# Copy into production folder
cp ${DEV_STRINGS}card.json ${PROD_STRINGS}card.json
cp ${DEV_STRINGS}powers.json ${PROD_STRINGS}powers.json
cp ${DEV_STRINGS}ui.json ${PROD_STRINGS}ui.json
cp ${DEV_STRINGS}pirurelic.json ${PROD_STRINGS}pirurelic.json
cp ${DEV_STRINGS}keywords.json ${PROD_STRINGS}keywords.json

# Replace strings
PROD_JSON=${PROD_STRINGS}card.json
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
sed -i s/\$recoverskill/"${RECOVER_SKILLS}"/g ${PROD_JSON}
sed -i s/\$recoverskillnohand/"${RECOVER_SKILLS_IGNORE_HAND}"/g ${PROD_JSON}
sed -i s/\$spreadcold/"${SPREAD_COLD}"/g ${PROD_JSON}
sed -i s/\$icebarrier/"${ICE_BARRIER}"/g ${PROD_JSON}
sed -i s/\$scoutattacks/"${SCOUT_ATTACKS}"/g ${PROD_JSON}
sed -i s/\$infiniteenergydiscard/"${INFINITE_ENERGY_DISCARD}"/g ${PROD_JSON}
sed -i s/\$infiniteupgrades/"${INFINITE_UPGRADES}"/g ${PROD_JSON}
sed -i s/\$nl/"${NL}"/g ${PROD_JSON}
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}powers.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}ui.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${PROD_STRINGS}pirurelic.json
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
  java -jar ../.local/share/Steam/steamapps/workshop/content/646570/1605060445/ModTheSpire.jar --skip-launcher
fi

# clean up after myself
finish "$@"
