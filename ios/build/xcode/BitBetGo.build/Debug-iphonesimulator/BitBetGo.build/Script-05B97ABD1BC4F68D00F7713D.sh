#!/bin/bash
FILE="${SRCROOT}/${PROJECT_NAME}/precopy.txt"
while read line; do
IFS=';' read -a stringarray <<< "$line"
cp -r ${stringarray[0]} ${stringarray[1]}

if [ -n "${stringarray[2]}" ]; then
    /usr/bin/codesign --force --sign "${stringarray[2]}" --preserve-metadata=identifier,entitlements --timestamp=none ${stringarray[3]}
fi

done < $FILE
