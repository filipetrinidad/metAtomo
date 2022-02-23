import nltk
from nltk.chat.util import Chat

def main(input):
    pairs = [["Meu nome é luiz", ["Olá luiz"]], ["Como você está", ["Eu estou bem"]], ["O que é átomo", ["É a menor estrutura possível de uma substância"]]]
    chat = Chat(pairs)
    outputData = chat.respond(input)
    return str(outputData)
