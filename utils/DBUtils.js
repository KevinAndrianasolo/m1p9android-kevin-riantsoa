const MongoClient = require('mongodb').MongoClient
let connectionString = "mongodb+srv://m1p9android-kevin-riantsoa:wjkmYLr7bASlL4yh@cluster0.i89jy.mongodb.net/m1p9android-kevin-riantsoa?retryWrites=true&w=majority";
let databaseName = "m1p9android-kevin-riantsoa";

var client = null;
var db = null;

let connect = async function(){
    if(!db) {
        client = await MongoClient.connect(connectionString, { useUnifiedTopology: true });
        db = client.db(databaseName);
    }
    return db;
}

module.exports.connect = connect;