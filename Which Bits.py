# Problem can be found at https://ccpc21d1.kattis.com/problems/ccpc21.whichbits
import sys

answer = []
bot_current = 0
top_current = 0
search = False

def recursive_scan(bot, top):
    global search, bot_current, top_current
    if not search and (bot_current <= top <= top_current):
        return

    print("q " + str(bot) + " " + str(top))
    sys.stdout.flush()
    bits_found = int(input())
    #print(str(bot_current) + " " + str(top_current))
    if bits_found == 0:
        return
    elif bits_found == 1 and not search:
        bot_current = bot
        top_current = top
        search = True

    if bot == top:
        search = False
        answer.append(bot)
        return

    mid = int(bot + (top - bot) / 2)
    recursive_scan(bot, mid)
    recursive_scan(mid + 1, top)


recursive_scan(0, 9223372036854775807) #9223372036854775807
print("a " + str(len(answer)) + " " + ' '.join(map(str, answer)))
