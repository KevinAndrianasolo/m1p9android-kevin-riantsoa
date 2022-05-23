

const JWT_KEY = "nothingtosay"
var bcrypt = require('bcryptjs');
const jwt = require("jsonwebtoken");
const pool =  require('../utils/PGconnect');
require('dotenv').config()

const  user_signup = async (request, response) => {
    let  name = request.body.name
    let firstname = request.body.firstname
    let username = request.body.username
    let email = request.body.email
    let birth = request.body.birth
    let password = bcrypt.hashSync(request.body.password, 10)
    let gender = request.body.gender  
    await pool.query('INSERT INTO account (name,firstname,username,email,birth,password,gender) values ($1,$2,$3,$4,$5,$6,$7)', 
    [name,firstname,username,email,birth,password,gender]
    , (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json({message: 'Account created'} )
    })
  }
  const user_login = async (request, response, next) => {
    let email = request.body.email
    
   await pool.query('SELECT * FROM account WHERE email = $1', [email], async(error, results) => {
      if (error || results.rowCount ==0) {
        return response.status(500).json({
            message:  "Email  invalide."
        });
      }
     await bcrypt.compare(request.body.password, results.rows[0]["password"], async (err, result) => {
            if (!result ) {
              return response.status(500).json({
                message:  "Mot de passe invalide."
              });
            }
            if (result) {
                const token = jwt.sign(
                    {
                        email: results.rows[0]["email"],
                        userId: results.rows[0]["id"]
                    },
                    process.env.JWT_KEY,
                    {}
                );
                return response.status(200).json({
                    message: "Authentification success",
                    token: token,
                    expiresIn: null
                });
            }
        });
    })
}   
  const user = function(req, res, next) {
    console.log(req.userData);
    if (req.userData) {
      console.log(req.userData);
      res.status(200).send(req.userData.userId);
      
      //next();
    } 
    else {
     return res.status(401).json({ message: 'Invalid token' });
    }
  }
  const  getUserById = async (request, response) => {
    const id = parseInt(request.params.id)
    
    await pool.query('SELECT * FROM account WHERE id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  }
  const  getUserDetails = async (request, response) => {
    if (request.userData) {
      const id = parseInt(request.userData.userId);
    
      await pool.query('SELECT * FROM account WHERE id = $1', [id], (error, results) => {
        if (error) {
          throw error
        }
        if(results.rows.length==0) throw new Error("Token invalide");
        response.status(200).json(results.rows[0])
      });
    } 
    else {
     return res.status(401).json({ message: 'Invalid token' });
    }
  }
  module.exports = {
    user_signup,user_login,user,getUserById, getUserDetails
  }


