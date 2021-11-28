const jwt = require('jsonwebtoken')

module.exports = (req, res, next) => {
    try {
        const decode = jwt.verify(req.headers.token, process.env.TOKEN_KEY)
        req.usuario =decode
        next()

    } catch (error) {
        return res.status(401).send({mensagem: "Falha na autenicação do usuario"})
    }
    
}