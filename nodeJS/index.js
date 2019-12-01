var crypto = require('crypto');
var uuid = require('uuid');
var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');


// Connect to mysql
var con = mysql.createConnection({
  host:'46.101.176.12',
  // bigchonk.com
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

  con.query('SELECT * FROM users WHERE email=?',[email],function(err,result,fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('MySQL error:',err);
    }

    if(result && result.length) {
      res.json('User already exists');
    }
    else {
      con.query('INSERT INTO `users`(`unique_id`, `name`, `email`, `encrypted_password`, `salt`, `created_at`, `updated_at`) VALUES (?,?,?,?,?,NOW(),NOW())',[uid,name,email,password,salt],function(err,result,fields){
        if(err) {
          console.log('[MySQL ERROR]', err);
          res.json('Register error:',err);
        }
      res.json('Register successful');
      })
    }
  });

})


app.post('/login/',(req,res,next)=>{
  var post_data = req.body;
  var user_password = post_data.password;
  var email = post_data.email;


  con.query('SELECT * FROM users WHERE email=?',[email],function(err,result,fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Login error:',err);
    }

    if(result && result.length){
      var salt = result[0].salt;
      var encrypted_password = result[0].encrypted_password;
      var hashed_password = checkHashPassword(user_password, salt).passwordHash;
      if(encrypted_password == hashed_password){
        res.end(JSON.stringify(result[0])) // If password is true, return all information of user
      } else {
        res.end(JSON.stringify('Wrong information'));
      }
    } else {
      res.json('User does not exist');
    }
  });


  con.query('SELECT * FROM users WHERE email=?',[email],function(error,result,fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Login error:',err);
    }
  })

})


app.post('/postReview/',(req,res,next)=>{
  var post_data = req.body;
  var gameId = post_data.game_id;
  var email = post_data.email;
  var password = post_data.password;
  var reviewRating = post_data.review_rating;
  var writtenReview = post_data.written_review;

  // Fist tests verifying user
  con.query('SELECT * FROM users WHERE email=?',[email],function(err,result,fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Credentials error:', err);
    }

    if(result && result.length){
      var salt = result[0].salt;
      var encrypted_password = result[0].encrypted_password;
      var hashed_password = checkHashPassword(user_password, salt).passwordHash;
      if(encrypted_password == hashed_password){
        // Password is true

        con.query('INSERT INTO `Reviews`(`game_id`, `unique_id`, `review_rating`, `written_review`) VALUES (?,?,?,?)',[gameId,unique_id,reviewRating,writtenReview],function(err,result,fields){
          if(err) {
            console.log('[MySQL ERROR]', err);
            res.json('Post error:', err);
          }
        res.json('Post successful');
        })



      } else {
        res.end(JSON.stringify('Wrong information'));
      }
    } else {
      res.json('User does not exist');
    }
  });

})


app.get('/getGamesList/',(req,res,next)=>{
  var post_data = req.body;
  var amount = post_data.amount;
  var page = post_data.page;

  var pagingOffset = amount * (page - 1);

  con.query('SELECT game_name FROM games LIMIT ? OFFSET pagingOffset ?', [amount, pagingOffset], function(err, result, fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Get error:', err);
    }
    res.json(result);
  });

});


app.get('/getProfileUser/',(req,res,next)=>{
  var post_data = req.body;
  var name = post_data.unique_id;

  con.query('SELECT name, created_at, updated_at FROM users WHERE unique_id = ?', [unique_id], function(err, result, fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Get error:', err);
    }
    res.json(result);
  })

})


app.get('/getProfileReview/',(req,res,next)=>{
  var post_data = req.body;
  var name = post_data.unique_id;

  con.query('SELECT game_name, review_rating, written_review FROM review WHERE unique_id = ?', [unique_id], function(err, result, fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Get error:', err);
    }
    res.json(result);
  })

})


app.get('/getGameInfo/',(req,res,next)=>{
  var post_data = req.body;
  var gameId = post_data.game_id;

  con.query('SELECT game_name, game_description FROM games WHERE game_id = ?', [gameId], function(err, result, fields){
    if(err) {
      console.log('[MySQL Error]', err);
      res.json('Get Error:', err);
    }
    res.json(result);
  })

})


app.get('/getGameReviews/',(req,res,next)=>{
  var post_data = req.body;
  var amount = post_data.amount;
  var page = post_data.page;
  var gameId = post_data.game_id;

  var pagingOffset = amount * (page - 1);

  con.query('SELECT unique_id, name, review_rating, written_review FROM reviews WHERE game_id = ? LIMIT ? OFFSET pagingOffset ?', [gameId, amount, pagingOffset], function(err, result, fields){
    if(err) {
      console.log('[MySQL ERROR]', err);
      res.json('Get error:', err);
    }
    res.json(result);
  });

})


app.get('/getSpecificGames/',(req,res,next)=>{
  var post_data = req.body;
  var gameId = post_data.game_id;

  var resultArray = new Array(gameId.length());

  for(i=0; i<=gameId.length(); i++){
    con.query('SELECT game_name FROM games WHERE game_id = ?', [gameId[i]], function(err, result, fields){
      if(err) {
        console.log('[MySQL ERROR]', err);
        res.json('Get error:', err);
      }
      resultArray.push(result);
    })
  }

  res.json(resultArray);

})



app.listen(3000, ()=> {
  console.log('Running on port 3000');
});
