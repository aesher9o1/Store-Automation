from mysql.connector import MySQLConnection, Error
from python_mysql_dbconfig import read_db_config
from visual import *
import serial
import pyttsx






#Arduino Graphics
smiley = sphere(radius= .5, color=color.yellow, pos=(0,0,-0.7))
eye = sphere(radius= 0.1, color= color.orange, pos=(0.19,0.2,-0.3))
eye2 = sphere(radius= 0.1, color= color.orange, pos=(-0.19,0.2,-0.3))
engine = pyttsx.init()

happiness = cylinder(pos=(-1,-0.7,-0.1),axis=(0,1,0), radius=0.07,length =0.01, color = color.red)

bulb1 = sphere(radius= 0.1, color=color.red, pos=(-0.5,-0.8,-0.7))
bulb2 = sphere(radius= 0.1, color=color.red, pos=(0,-0.8,-0.7))
bulb3 = sphere(radius= 0.1, color=color.red, pos=(0.5,-0.8,-0.7))

lengthLabel = label(pos=(0,0.5,0), text='Welcome to Future Automated Store', box=false, height=30)

arduinoSerialData = serial.Serial('com3',9600)









#Hard Coded Strings out here
manipal = "Manipal University, Jaipur is one of the premier universities in Jaipur, India. It is the fifth university established under i Manipal Group. Courses and programs are being offered by the university like Law, Engineering, Medical, Hospitality, Allied Health, Management, Communication, Jewellery Management etc. to enhance the interest of students in all fields. It is near Dehmi Kalan village, Tehsil Sanganer, Jaipur district."
eai = "ICCT17 will serve as a platform for knowledge sharing about the recent trends and advancements in the field of networking and high end data handling and how these domains are playing role in research and market development of the industries. It will provide great opportunity for our students and faculties to interact and share ideas with the top-notch in the field face to face. This knowledge sharing may inspire and thrill many young minds and help us bring collaborations and global partners to work together. This will enable us to solve challenging problems in our society so that we may contribute to our world. The whole idea of the forum is to exchange thoughts and ideas, transform those in real time to solve the problems. Conference will also create awareness in students about the importance of scientific research in related fields and synchronizing with product market."













#SQL variable
global user_id
user_id=5

global eminem
eminem=0

global beyounce
beyounce=0









 
#if __name__ == '__main__'
#   update(1,1)














#This here upadtes the table 
 
def update(book1,book2):
    db_config = read_db_config()
    query = """ UPDATE users
                SET Eminem = %s, Beyonce = %s
                WHERE Rid = %s """

    data = (book1, book2, user_id)
    try:
        print('Connecting to MySQL database...')
        conn = MySQLConnection(**db_config)
 
        if conn.is_connected():
            cursor = conn.cursor()
            cursor.execute(query, data)
            conn.commit()
        else:
            print('connection failed.')
 
    except Error as error:
        print(error)
 
    finally:
        conn.close()
        print('Connection closed.')























#This here fetches the table

def fetch():
    global eminem
    global beyounce
    try:
        dbconfig = read_db_config()
        conn = MySQLConnection(**dbconfig)
        cursor = conn.cursor()
        data = (user_id,)
        
        query = "SELECT Eminem FROM users WHERE Rid = %s "
        cursor.execute(query, data)
        data = cursor.fetchone()
        global eminem
        eminem = data[0]
        
        query = "SELECT Beyonce FROM users WHERE Rid = %s "
        cursor.execute(query, data)
        data = cursor.fetchone()
        global beyounce
        beyonce = data[0]
        
        print(eminem)
        print(beyounce)
 
    except Error as e:
        print(e)
 
    finally:
        cursor.close()
        conn.close()




















#arduino Logic
while(True):
    rate(50)
    if (arduinoSerialData.inWaiting()>0):
         myData = arduinoSerialData.readline()
         myData = str(myData).strip()
         print(myData)
         
         if str(myData)=="Oon":
             lengthLabel.text = "Bulb One Was Turned On"
             bulb1.color= color.yellow
         
         elif myData=="Ooff":
             lengthLabel.text = "Bulb One Was Turned Off"
             bulb1.color=color.red

         elif myData=="Ton":
             lengthLabel.text = "Bulb Two Was Turned On"
             bulb2.color= color.yellow

         elif myData=="Toff":
             lengthLabel.text = "Bulb Two Was Turned Off"
             bulb2.color=color.red

         elif myData=="THon":
             lengthLabel.text = "Bulb Three Was Turned On"
             bulb3.color=color.yellow

         elif myData=="THoff":
             lengthLabel.text = "Bulb Three Was Turned Off"
             bulb3.color=color.red
         
         elif myData[0]=="S":
            lengthLabel.text = "Smile Please?!"
            smiley.color=color.blue
            happiness.length = random.uniform(1, 3)/100;
            happiness.color= color.red
        
            
         elif myData[0]=="T":
            lengthLabel.text = "Why So Pale?!"
            smiley.color=color.white
            happiness.length = random.uniform(20, 40)/100;
            happiness.color= color.yellow
        

         elif myData[0]=="W":
            lengthLabel.text = "You Look Great"
            smiley.color=color.yellow
            happiness.length = random.uniform(70, 100)/100;
            happiness.color= color.green


         elif myData=="Manipal":
            happiness.length = random.uniform(1, 3)/100;
            happiness.color= color.red
            engine.say(manipal)
            engine.runAndWait()

         elif myData=="EAI":
            happiness.length = random.uniform(1, 3)/100;
            happiness.color= color.red
            engine.say(eai)
            engine.runAndWait()
             
         elif myData[0]=="!":
             myData[0] == " "
             engine.say(myData)
             engine.runAndWait()
             if myData[1]=="@":
                myData[1] == " "
                fetch()
                update(3,3)    
    
         elif myData=="tushar":
            user_id = 1
            lengthLabel.text = "Hello Mr Tushar :"
            print(user_id)

         elif myData=="Aashis":
            lengthLabel.text = "Hello Mr Aashis :"
            user_id = 3
            print(user_id)

         elif myData=="Vidu":
            lengthLabel.text = "Hello Mr Vidu :"
            user_id = 4
            print(user_id)

         elif myData=="Sid":
            lengthLabel.text = "Hello Mr Siddhart Jaidka :"
            user_id = 5
            print(user_id)

































