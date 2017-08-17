from flask import Flask
import MySQLdb
from flask import jsonify,request,session
app = Flask(__name__)

db = MySQLdb.connect(host="localhost", port=3306,user="root", passwd="paybhama", db="paybhama")
cursor = db.cursor()

@app.route('/test')
def hello():
	cursor.execute("SELECT * from tbl_login")
	res = cursor.fetchall()
	#print res 
	return  jsonify(res)

@app.route('/login',methods=["GET","POST"])
def login():
	customer_id = request.form.get('customer_id')
	password = request.form.get('password')
	print customer_id;
	print password;
	cursor.execute("select COUNT(1) from tbl_login where customer_id = '"+str(customer_id)+"' and password = '"+str(password)+"'")
	res = cursor.fetchone()
	if res[0]:
		cursor.execute("select * from tbl_login where customer_id= '"+str(customer_id)+"' and password = '"+str(password)+"'")
		res = cursor.fetchone()
		return jsonify(res)
	else :
		return jsonify(["failure"])

@app.route('/signup',methods=["GET","POST"])
def signup():
	aadhar_number = request.form.get('aadhar_number')
	bank_name = request.form.get('bank_name')
	account_number = request.form.get('account_number')
	ifsc_code = request.form.get('ifsc_code')
	customer_id = request.form.get('customer_id')
	email = request.form.get('email')
	first_name = request.form.get('first_name')
	last_name = request.form.get('last_name')
	gender = request.form.get('gender')
	location = request.form.get('location')
	password = request.form.get('password')
	district = request.form.get('district')
	print aadhar_number
	print bank_name
	print account_number
	print ifsc_code
	print customer_id
	print email
	print first_name
	print last_name
	print gender 
	print location 
	print password
	print district
	#INSERT INTO `tbl_login`(`customer_id`, `password`, `aadhar`, `bank`, `bank_ac`, `email`, `ifsc_code`, `first_name`, `last_name`, `gender`, `address`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7],[value-8],[value-9],[value-10],[value-11]);
	cursor.execute("INSERT INTO `tbl_login`(`customer_id`, `password`, `aadhar`, `bank`, `bank_ac`, `email`, `ifsc_code`, `first_name`, `last_name`, `gender`, `location`,`district`) VALUES ('"+str(customer_id)+"', '"+str(password)+"','"+str(aadhar_number)+"','"+str(bank_name)+"','"+str(account_number)+"','"+str(email)+"','"+str(ifsc_code)+"','"+str(first_name)+"','"+str(last_name)+"','"+str(gender)+"','"+str(location)+"','"+str(district)+"')")


if __name__ == '__main__':
    app.run(host="ec2-34-204-44-57.compute-1.amazonaws.com", port=8081, debug=True)