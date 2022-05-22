const pool =  require('../utils/PGconnect');

const  getAllCourseByTheme = async (request, response) => {
    const id = parseInt(request.params.id)
    
    await pool.query('SELECT * FROM Course WHERE courseTheme_id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
}
const  getCourseById = async (request, response) => {
  const id = parseInt(request.params.id)
  
  await pool.query('SELECT * FROM Course WHERE id = $1', [id], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}
const  getCourseByTitle = async (request, response) => {
  const id =  parseInt(request.params.id)
  const title = request.query.title
  const query = "SELECT * FROM Course WHERE LOWER(title) like LOWER('%"+title+"%') and courseTheme_id ="+id
  await pool.query(query, (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}


module.exports = {
    getAllCourseByTheme,
    getCourseById,
    getCourseByTitle
  }