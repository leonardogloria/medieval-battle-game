import requests
import sys
import os


URL = "http://"+  os.environ.get("URL", "localhost") +":8080/"
USER_NAME = ""
def boasVindas():
    print(URL + " ===================== ")
    print("Seja bem vindo ao Medieval Battle")
    print("Digite o seu nome de usuario")
    playerName = input()
    data = {'name' : playerName}
    global USER_NAME 
    USER_NAME = playerName

    r = requests.post(url = URL + 'player' , json = data)
    if(r.status_code == 200):
        print("Seja vem vindo " + playerName)
    else:
        print("Seja vem vindo de volta " + playerName)
def getPerfil():
    print(chr(27)+'[2j')
    print('\033c')
    print('\x1bc')
    r = requests.get(url = URL + 'player/' + USER_NAME)
    print("Nickname: " + r.json()['name'])
    print("Pontos de XP : " + str(r.json()['xp']))
def getRanking():
    print(chr(27)+'[2j')
    print('\033c')
    print('\x1bc')
    r = requests.get(url = URL + 'ranking' )
    data = r.json()
    for idx,user in enumerate(data):
        print( str(idx ) + "- " + user['name'] + " --- Pontos: " + str(user['xp']))
def getDuels():
    print(chr(27)+'[2j')
    print('\033c')
    print('\x1bc')
    r = requests.get(url = URL + "/duel/player/" + USER_NAME)
    data = r.json()
    for duel in data:
        print(duel['hero'] + "  vs  " + duel['monster'] + "------ Winer: " + duel['winner'] )
        duelDetail = duel['actions']
        for action in duelDetail:
            print("Round: " + str(action['round']) + " " + action['striker'] + "ataca: " + action['defender'] + " dano: " + str(action['dano']))
    #print(r.json())


def duelar():
    print(chr(27)+'[2j')
    print('\033c')
    print('\x1bc')
    print("Escolha seu personagem")
    print("1 - Guerreiro")
    print("2 - Barbaro")
    print("3 - Paladino")
    personagem = input()
    if personagem == '1': 
        personagem = 'guerreiro'
    elif personagem == '2':
        personagem = 'barbaro'
    elif personagem == '3':
        personagem = 'paladino'
    data = {'username' : USER_NAME, 'character': personagem}
    r = requests.post(url= URL + "/startGame", json= data)
    print("ID do duelo: " + r.json()['uuid'] + " vencedor: " + r.json()['winner'] + " rounds:" + str(r.json()['rounds'] ) )
    if 'xp' in r.json():
        print( " pontos ganhos: "  + str(r.json()['xp']))
    




def menu():

    print("Digite a opcao desejada:")
    print("1- Ver meu perfil")
    print("2- Ver Ranking")
    print("3 - Duelar")
    print("4 - Ver duelos")

    print("Q - Sair")

    option = input()
    if option == '1':
        getPerfil()
    elif option == '2':
        getRanking()
    elif option == '3':
        duelar()
    elif option == '4':
        getDuels()
    elif option == 'Q':
        return False 
    return True
boasVindas()
continueGame = True
while(continueGame):
    continueGame = menu()

