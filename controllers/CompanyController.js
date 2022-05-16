const pool =  require('../utils/PGconnect');

const getCompanies = async(request, response) => { 
    await pool.query('SELECT * FROM company ORDER BY company_id ASC', (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  }

const getCompanyById = async (request, response) => {
    const id = parseInt(request.params.id)
    
    await pool.query('SELECT * FROM company WHERE company_id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
}

const getCompanyWithRequest = async(request, response) => {
    const id = parseInt(request.params.id)
    
    await pool.query('SELECT * FROM company WHERE company_id = $1', [id], (error, results) => {
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