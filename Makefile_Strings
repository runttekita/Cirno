#!/bin/bash

TARGET=./src/main/resources/Piruru/localization/eng/prodStrings/
SOURCE=./src/main/resources/Piruru/localization/eng/

# LOCALIZERS CHANGE THIS
DAMAGE="Deal !D! Damage."
BLOCK="Gain !B! Block."
DISCARD_ONE="Discard 1 card."
ENERGY="Gain !M! [E] ."
DRAW="Draw !M! cards."
COLD="Apply !M! Piruru:Cold."

cp ${SOURCE}card.json ${TARGET}card.json
cp ${SOURCE}powers.json ${TARGET}powers.json
cp ${SOURCE}ui.json ${TARGET}ui.json
cp ${SOURCE}pirurelic.json ${TARGET}pirurelic.json

PROD_JSON=${TARGET}card.json
sed -i s/\$damage/"${DAMAGE}"/g ${PROD_JSON}
sed -i s/\$block/"${BLOCK}"/g ${PROD_JSON}
sed -i s/\$discardOne/"${DISCARD_ONE}"/g ${PROD_JSON}
sed -i s/\$energy/"${ENERGY}"/g ${PROD_JSON}
sed -i s/\$draw/"${DRAW}"/g ${PROD_JSON}
sed -i s/\$cold/"${COLD}"/g ${PROD_JSON}
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${TARGET}powers.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${TARGET}ui.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}

PROD_JSON=${TARGET}pirurelic.json
sed -i s/'[[:alnum:]]*": {'/'Piruru:&'/g ${PROD_JSON}


