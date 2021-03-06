var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');
var cors = require('cors');
var env = require('dotenv').config()
var indexRouter = require('./routes/index');
var apiRouter = require('./routes/api');
var authRouter = require('./routes/auth');

const routes = require('./routes');
// Middleware
var AuthMiddleware = require('./middleware/AuthMiddleware');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');


app.use(cors());
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use('/public', express.static('public'));

// app.use(express.static(path.join(__dirname, 'm1p9mean-kevin-client/dist/m1p9mean-kevin-client')));

// app.use(AuthMiddleware);

// app.use('/', indexRouter);
// app.use('/api/reflect', apiRouter);
// app.use('/auth', authRouter);
/// les routes 

app.use('/', routes);


// catch 404 and forward to error handler
// app.use(function(req, res, next) {
//   next(createError(404));
// });

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
