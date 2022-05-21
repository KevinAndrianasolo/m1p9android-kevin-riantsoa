const pool =  require('../utils/PGconnect');

const  getCourseThemeByCompany = async (request, response) => {
    const id = parseInt(request.params.id)
    
    await pool.query('SELECT * FROM CourseTheme WHERE company_id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
}

module.exports = {
    getCourseThemeByCompany
  }