#!/bin/bash

USERNAME=$1
if [[ -z $USERNAME ]]; then
	echo "Usage turguser.sh <User Name>"
else
	COMMAND="curl -X GET http://localhost:8080/turfgame/user/$USERNAME"
	echo $COMMAND
	echo "Getting turfuser data for $USERNAME"
	echo "USERNAME = $USERNAME" 
	$COMMAND
fi
