const express = require('express')
const router = express.Router()
const mysql = require('../mysql').pool
const bcrypt = require('bcrypt')


router.post('/login', (req, res, next) => {

    mysql.getConnection((error, conn) => {
        conn.query(
            'SELECT emailUsuario FROM usuario WHERE emailUsuario = ? AND senhaUsuario = ?',
            [req.body.emailUsuario, req.body.senhaUsuario],
            (error, resultado, fields) => {
                conn.release()

                if (error) {
                    return res.status(500).send({ error: error })
                }

                res.send(resultado)
            }
        )
    })

})

router.get('/cadastro', (req, res, next) => {
    res.send({
        message: "bomdia"
    })
})

router.post('/cadastro', (req, res, next) => { 
    console.log(res)   

    mysql.getConnection((error, conn) => {

        if (error) { return res.status(500).send({ "error": error }) }
        bcrypt.hash(req.body.senhaUsuario, 10, (bcryptError, hash) => {
            if (bcryptError) { return res.status(500).send({ "error": bcryptError }) }
            conn.query(
                'INSERT INTO usuario VALUES (?,?,?)',
                [req.body.emailUsuario, hash, req.body.nomeUsuario],
                (error, resultado) => {
                    conn.release()
                    if(error) { console.log(error.message);return res.status(500).send({"error": error.message})}
                    const response = "Usuario criadio :)"                    
                    res.status(201).send({"mensagem":"usuario criado"}); 

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