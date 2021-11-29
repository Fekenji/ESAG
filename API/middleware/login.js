const jwt = require('jsonwebtoken')

module.exports = (req, res, next) => {
    try {
        const token = req.headers.authorization.split(' ')[1]
        const decoded = jwt.verify(token, process.env.TOKEN_KEY)
        req.usuario = decoded
        next()

    } catch (error) {
        return res.status(401).send({mensagem: "Falha na autenicação do usuario"})
    }
    
}