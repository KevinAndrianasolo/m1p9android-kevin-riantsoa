const pool =  require('../utils/PGconnect');

const getCompanies = async(request, response) => { 
    await pool.query('SELECT * FROM company ORDER BY id ASC', (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  }

const getCompanyById = async (request, response) => {
    const id = parseInt(request.params.id)
    
    await pool.query('SELECT * FROM company WHERE id = $1', [id], (error, results) => {
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