var crypto = require('crypto');
var uuid = require('uuid');
var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');


// Connect to mysql
var con = mysql.createConnection({
  host:'localhost',
  user:'root',
  password:'',
  database:'review_database'
});












var genRandomString = function(length) {
  // creates hexidecimal random number and returns up to the inputted length
  return crypto.randomBytes(Math.ceil(length/2)).toString('hex').slice(0,length);
}

var sha512 = function(password, salt){
  var hash = crypto.createHmac('sha512', salt);
  hash.update(password);
  var value = hash.digest('hex');
  return {
    salt:salt,
    passwordHash:value
  }
}

function saltHashPassword(userPassword){
  var salt = genRandomString(16);
  var passwordData = sha512(userPassword,salt);
  return passwordData;
}


function checkHashPassword(userPassword, salt){
  var passwordData = sha512(userPassword,salt);
  return passwordData;
}






var app = express();
app.use(bodyParser.json()); // Accepts JSON Params
app.use(bodyParser.urlencoded({extended: true})); // Accepts URL Encoded Params


app.post('/register/',(req, res, next)=>{
  var post_data = req.body;

  var uid = uuid.v4();
  var plain_password = post_data.password;
  var hash_data = saltHashPassword(plain_password);
  var password = hash_data.passwordHash;
  var salt = hash_data.salt;

  var name = post_data.name;
  var email = post_data.email;

  con.query('SELECT * FROM user where email=?',[email],function(err,result,fields){
    con.on('error', function(err){
      console.log('MySQL ERROR',err);
    });

    if(result && result.length) {
      res.json('User already exists');
    }
    else {
      con.query('INSERT INTO `user`(`unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,NOW(),NOW())',[uid,name,email,password,salt],function(err,result,fields){
      con.on('error', function(err){
        console.log('[MySQL ERROR]',err);
        res.json('Register error:',err);
      });
      res.json('Register successful');
      })
    }
  });

})


app.post('/login/',(req,res,next)=>{
  var post_data = req.body;
  var user_password = post_data.password;
  var email = post_data.email;


  con.query('SELECT * FROM user where email=?',[email],function(err,result,fields){
    con.on('error',function(err){
      console.log('[MYSQL ERROR]',err);
    });

    if(result && result.length){
      var salt = result[0].salt;
      var encrypted_password = result[0].encrypted_password;
      var hashed_password = checkHashPassword(user_password, salt).passwordHash;
      if(encrypted_password == hashed_password){
        res.end(JSON.stringify(result[0])) // If password is true, return all infor of user
      } else {
        res.end(JSON.stringify('Wrong information'));
      }
    } else {
      res.json('User does not exist');
    }
  });



  con.query('select * from user where email=?',[email],function(error,result,fields){
    con.on('error', function(err){
      console.log('[MySQL ERROR]',err);
      res.json('Login error:',err);
    });




  })
})


// app.get("/", (req, res, next)=>{
//   console.log('Password: 123456');
//   var encrypt = saltHashPassword("123456");
//   console.log('Encrypt: ' + encrypt.passwordHash);
//   console.log('Salt: ' + encrypt.salt);
// })





app.listen(3000, ()=> {
  console.log('Running on port 3000');
});