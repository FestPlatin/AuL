#!/usr/bin/python3

import sys
import math

if __name__ == "__main__":
    dictWords={}
    numClasses=len(set(sys.argv[2:]))
    classes=set(sys.argv[2:])
    print(numClasses, classes)
    langs=[]
    for file in classes:
        f=open(file,"r")
        text=f.read()
        words=text.split()
        for word in words:
            if word not in dictWords:
                dictWords[word]={}
                for c in classes:
                    dictWords[word][c]=0
                    dictWords[word]["sum"]=0
            dictWords[word][file]+=1
            dictWords[word]["sum"]+=1
        f.close()
        langs+=[file]


#    for word in dictWords:
#        dictWords[word]["sum"]=0
#        for c in classes:
#            dictWords[word]["sum"]+=dictWords[word][c]

    # klassifizieren
    pmax=0.0
    cmax=""
    f=open(sys.argv[1])
    text=f.read()
    words=text.split()
    for c in classes:
        prod=math.log(1/numClasses)
        for word in words:
            if word in dictWords and dictWords[word][c] > 0:
                diff=dictWords[word][c]/dictWords[word]["sum"]
                prod+=math.log(diff)
            else:
                prod+=math.log(0.00000001)

        if prod > pmax or pmax==0.0:
            pmax=prod
            cmax=c
        print(c, prod)

    print("\nKlasse: ",cmax, pmax)


