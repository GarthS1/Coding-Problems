import sys

t = int(input())

for test in range(t):
    n = int(input())
    input_list = list(map(int, sys.stdin.readline().strip().split()))
    cost = 0
    for i in range(len(input_list) - 1):
        index = input_list.index(min(input_list[i:]))
        input_list[i:index+1] = reversed(input_list[i:index+1])
        cost += index - i + 1
        #print(input_list)

    print("Case #" + str(test+1) + ": " + str(cost))
