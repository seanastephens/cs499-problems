import sys

def runThing(x):
    count = 1
    for i in range(2,int(2*(x**.5))):
        if i % 2 == 0:
            if (x % i)==(i/2) and ((i)*(i+1)/2 <= x):
                count += 1
        elif x % i == 0:
            count += 1
    return count
        

if __name__=="__main__":
    for line in sys.stdin:
        x = int(line.strip())
