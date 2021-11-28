const express = require('express')
const router = express.Router()
const mysql = require('../mysql').pool
const bcrypt = require('bcrypt')
const jwt = require('jsonwebtoken')


router.get('/cadastro', (req, res, next) => {
    res.send({
        message: "bomdia"
    })
})

router.post('/cadastro', (req, res, next) => {     
    mysql.getConnection((error, conn) => {

        if (error) { return res.status(500).send({ error: error }) }
        bcrypt.hash(req.body.senhaUsuario, 10,(bcryptError, hash) => {
            if (bcryptError) { return res.status(500).send({ error: bcryptError }) }            
            conn.query(
                'INSERT INTO usuario VALUES (?,?,?)',
                [req.body.emailUsuario, hash, req.body.nomeUsuario],
                (error, resultado) => {
                    conn.release()
                    if(error) { console.log(error.message);return res.status(500).send({error: error})}                   
                    res.status(201).send({mensagem:"usuario criado"}); 

                }
            )
        })
    })
})

router.post('/login', (req, res, next) => {
    mysql.getConnection((error, conn) => {
        
        if(error) { return res.status(500).send({ "error": error }) }
        conn.query(
            'SELECT * FROM USUARIO WHERE emailUsuario = ?',
            [req.body.emailUsuario],
            (error, results) =>{                
                conn.release()
                if(error) { return res.status(500).send({ error: error }) }
                if(results.lenght < 1) { return res.status(401).send({ mensagem: "Erro na autenticação de usuario"}) }

                bcrypt.compare(req.body.senhaUsuario, results[0].senhaUsuario, (error, resultado) => {
                    if (error) { 
                        return res.status(401).send({ mensagem: "Erro na autenticação de usuario"}) 
                    }
                    if(resultado) {
                        const token = jwt.sign({
                            emailUsuario: results[0].emailUsuario,
                            nomeUsuario: results[0].nomeUsuario
                        }, process.env.TOKEN_KEY,
                        {
                            expiresIn: "2d"
                        })
                        return res.status(200).send({ 
                            mensagem: "Usuario autenticado",
                            token: token
                        })
                    }
                    return res.status(401).send({ mensagem: "Erro na autenticação de usuario"}) 
                })
            }
        )
    })
})


router.post('/alteracao', (req, res, next) => {
    mysql.getConnection((error, conn) => {

        if (error) { return res.status(500).send({ error: error }) }
        bcrypt.hash(req.body.senhaUsuario, 10,(bcryptError, hash) => {
            if (bcryptError) { return res.status(500).send({ error: bcryptError }) }            
            conn.query(
                'UPDATE usuario set senhaUsuario = ? where emailUsuario = ?',
                [hash, req.body.emailUsuario],
                (error, resultado) => {
                    conn.release()
                    if(error) { console.log(error.message);return res.status(500).send({error: error})}               
                    res.status(201).send({mensagem:"senha modificada"}); 
                }
            )
        })
    })
})

router.patch('/usuarios', (req, res, next) => {


    mysql.getConnection((error, conn) => {
        conn.query(
            'UPDATE usuario SET senhaUsuario = ? where emailUsuario = ?',
            [req.body.senhaUsuario, req.body.emailUsuario],
            (error, resultado, fields) => {
                conn.release()

                if (error) {
                    return res.status(500).send({
                        error: error.message,
                        response: null
                    })
                }

                res.status(201).send({
                    mensagem: "usuario atualizado",
                    novaSenha: resultado.senhaUsuario
                })
            }
        )
    })

})

module.exports = router