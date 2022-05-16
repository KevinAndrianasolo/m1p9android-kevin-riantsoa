const pool =  require('../utils/PGconnect');


const getAsset = async (request, response) => { 

    let idCompany = request.query.company_id
    let idCategory = request.query.category_id
    let valueAsset = request.query.asset_value
    let champAsset = request.query.asset_field

    let query = 'SELECT * FROM asset '
    if( Object.keys(request.query).length !== 0) {
        query += 'WHERE '
        if(typeof idCompany   !== 'undefined' ){
            query += 'company_id = '+ idCompany 
        }
        if(typeof idCategory  !== 'undefined' ){
            if(idCompany  !== 'undefined' ){
                query += ' AND '  
            }
            query += 'category_id = '+ idCategory 
        }
        if(typeof champAsset  !== 'undefined' ){
            if(typeof idCompany  !== 'undefined' || typeof idCategory  !== 'undefined' ){
                query += ' AND '  
            }
            query += champAsset+" = '"+ valueAsset +"'"
        }
    }
   await pool.query(query, (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).json(results.rows)
      })
  }

const getAssetById = async (request, response) => {

    const id = parseInt(request.params.id) 
   await pool.query('SELECT * FROM asset WHERE asset_id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
}

module.exports = {
   getAsset,
   getAssetById
  }