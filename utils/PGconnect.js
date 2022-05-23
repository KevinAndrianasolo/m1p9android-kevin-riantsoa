require('dotenv').config()
const { Pool } = require('pg');
var pg = require("pg");

const pool = new pg.Client({
    user: process.env.PGUSER,
    host: process.env.PGHOST,
    database: process.env.PGDATABASE,
    password: process.env.PGPASSWORD,
    port: process.env.PGPORT,
    ssl: true
  });


module.exports = pool;