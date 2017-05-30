def text_Summary(text):
    wordlist = {}
    text_nopunc = "".join(c for c in text if c not in ('!','.',':',','))
    for word in text_nopunc.split():
        if word in wordlist:
            wordlist[word] += 1
        else:
            wordlist[word] = 1
    # return wordlist  # now has the occurrences of each word
    text_copy = text
    sentence_list = []
    while '.' in text_copy:
        sentence_list.append(text_copy[0:(text_copy.find('.'))])
        text_copy = text_copy[0:(text_copy.find('.'))]
    return sentence_list


alist = [12, 0, 45]
blist = ['b1', 'b2', 'b3']
for i, (a, b) in enumerate(zip(alist, blist)):
    if a == 0:
        blist[i] = b + ' ' + str(a)


[(blist[i] = b + ' ' + str(a)) for i, (a, b) in enumerate(zip(alist, blist) if a == 0)]
