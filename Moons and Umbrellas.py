#https://codingcompetitions.withgoogle.com/codejam/round/000000000043580a/00000000006d1145

# finds the cost of a given string
def find_cost(cost_string):
    global x, y
    cost = 0

    if not isinstance(cost_string, list):
        cost_string = list(cost_string)

    for i in range(len(cost_string) - 1):
        if cost_string[i:i + 2] == ['C', 'J']:
            cost += x
        elif cost_string[i:i + 2] == ['J', 'C']:
            cost += y
    return cost

# finds the min cost of a given substring
def find_min_cost(cost_string):
    cost_string = list(cost_string)
    start_index = 0
    end_index = len(cost_string) - 1

    # If all ? return all J
    if cost_string.count('?') == len(cost_string):
        return "J" * len(cost_string)

    while True:

        # if the index equal each other there are no more ?
        if start_index == end_index:
            return cost_string

        # if substring looks like char, ?, char
        if start_index + 2 < len(cost_string):
            if cost_string[start_index] != '?' and cost_string[start_index + 1] == '?' and cost_string[start_index + 2] != '?':
                temp = [find_cost(cost_string[start_index] + 'J' + cost_string[start_index + 2]),
                        find_cost(cost_string[start_index] + 'C' + cost_string[start_index + 2])]
                if temp.index(min(temp)) == 0:
                    cost_string[start_index: start_index + 3] = cost_string[start_index] + 'J' + cost_string[start_index + 2]
                else:
                    cost_string[start_index: start_index + 3] = cost_string[start_index] + 'C' + cost_string[start_index + 2]
                return cost_string

        # looks like char + ? .... ? + char
        if cost_string[start_index] != '?' and cost_string[end_index] != '?':
            temp_start = [find_cost(cost_string[start_index] + 'J'), find_cost(cost_string[start_index] + 'C')]
            temp_end = [find_cost('J' + cost_string[end_index]), find_cost('C' + cost_string[end_index])]

            # add on to the end
            if min(temp_start) > min(temp_end):
                if temp_end.index(min(temp_end)) == 0:
                    cost_string[end_index - 1:end_index + 1] = 'J' + cost_string[end_index]
                else:
                    cost_string[end_index - 1:end_index + 1] = 'C' + cost_string[end_index]
                end_index -= 1

            # add on to the start
            else:
                if temp_start.index(min(temp_start)) == 0:
                    cost_string[start_index: start_index + 2] = cost_string[start_index] + 'J'
                else:
                    cost_string[start_index: start_index + 2] = cost_string[start_index] + 'C'
                start_index += 1

        # looks like char + ? .... ?
        elif cost_string[start_index] != '?' and cost_string[end_index] == '?':
            temp_start = [find_cost(cost_string[start_index] + 'J'), find_cost(cost_string[start_index] + 'C')]

            if temp_start.index(min(temp_start)) == 0:
                cost_string[start_index: start_index + 2] = cost_string[start_index] + 'J'
            else:
                cost_string[start_index: start_index + 2] = cost_string[start_index] + 'C'

            start_index += 1

        # looks like ? .... ? + char
        elif cost_string[start_index] == '?' and cost_string[end_index] != '?':
            temp_end = [find_cost('J' + cost_string[end_index]), find_cost('C' + cost_string[end_index])]

            if temp_end.index(min(temp_end)) == 0:
                cost_string[end_index - 1:end_index + 1] = 'J' + cost_string[end_index]
            else:
                cost_string[end_index - 1:end_index + 1] = 'C' + cost_string[end_index]

            end_index -= 1


t = int(input())

for test in range(t):
    x, y, string = input().split(" ")
    x = int(x)
    y = int(y)
    string = list(string)
    index = 0
    curr_string = ""

    while index < len(string):
        if string[index] == '?':
            curr_string += string[index]
        else:
            curr_string += string[index]
            if '?' in curr_string:
                string[index - len(curr_string) + 1: index + 2] = find_min_cost(curr_string)
            curr_string = curr_string[-1]
        index += 1

    if '?' in curr_string:
        string[index - len(curr_string) + 1:index + 1] = find_min_cost(curr_string)

    print("Case #" + str(test+1) + ": " + str(find_cost(string)))
