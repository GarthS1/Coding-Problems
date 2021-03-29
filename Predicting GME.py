# problem can be found at https://ccpc21d1.kattis.com/problems/ccpc21.predictinggme
# This solution will not work. I tried to sort the list to mazimize profit while the solution would be to keep more of a table fomrat of 
# each day

import sys

days = int(input())
predictions = list(map(int, sys.stdin.readline().strip().split()))
predict_with_index = []

# First true can sell, other true is can buy
for i in range(len(predictions)):
    predict_with_index.append([predictions[i], i, True])


total_profit = 0

sort_list = sorted(predict_with_index, key=lambda x : x[0])
#print(sort_list)

start_index = 0
end_index = len(sort_list) - 1
while start_index < end_index:
    if sort_list[start_index][1] < sort_list[end_index][1] and sort_list[end_index][2]:
        total_profit += sort_list[end_index][0] - sort_list[start_index][0]
        remove = [sort_list[end_index], sort_list[start_index]]
        for i in range(len(sort_list)):
            if sort_list[i][1] == sort_list[end_index][1] + 1:
                remove.append(sort_list[i])
            elif sort_list[i][1] == sort_list[end_index][1] - 1:
                sort_list[i][2] = False
            elif sort_list[i][1] == sort_list[start_index][1] - 1:
                sort_list[i][2] = False

        for i in remove:
            if i in sort_list:
                sort_list.remove(i)

        start_index = 0
        end_index = len(sort_list) - 1
    else:
        start_index += 1
    #print(sort_list)

print(total_profit)
