#!/bin/bash
if [ $# -lt 2 ] ; then
 echo "Synt.: $0 fichier filtre1 [filtre2]" ; exit 1
fi
while read ligne
do 
 case $ligne in
  *$2*$3*)
  ligne=${ligne#*$2 }
  ligne=${ligne%%,*}
  echo "[$2]: $ligne"
  ;;
 esac
done <$1
