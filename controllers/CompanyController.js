const pool =  require('../utils/PGconnect');

const getCompanies = (request, response) => { 
    pool.query('SELECT * FROM company ORDER BY company_id ASC', (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  }

const getCompanyById = (request, response) => {
    const id = parseInt(request.params.id)
    
    pool.query('SELECT * FROM company WHERE company_id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
}

const getCompanyWithRequest = (request, response) => {
    const id = parseInt(request.params.id)
    
    pool.query('SELECT * FROM company WHERE company_id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
}
module.exports = {
    getCompanyById,
    getCompanies
  }