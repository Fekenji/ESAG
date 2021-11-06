const express = require('express')
const router = express.Router()
const mysql = require('../mysql').pool


router.post('/login', (req,res,next) => {
    
    mysql.getConnection((error, conn) => {
        conn.query(
            'SELECT emailUsuario FROM usuario WHERE emailUsuario = ? AND senhaUsuario = ?',
            [req.body.emailUsuario, req.body.senhaUsuario],
            (error, resultado, fields) => {
                conn.release()

                if(error){
                    return res.status(500).send({error: error})
                }

                res.send(resultado)
            }
        )
    })

})

router.post('/usuarios', (req,res,next) => {

    mysql.getConnection((error, conn) => {
        conn.query(
            'INSERT INTO usuario VALUES (?,?,?,?)',
            [req.body.emailUsuario,req.body.senhaUsuario,req.body.telefoneUsuario,req.body.nomeUsuario],
            (error, resultado, fields) => {
                conn.release()

                if(error){
                    return res.status(500).send({
                        error:error.message,
                        response:null
                    })
                }

                const usuario = {
                    email: resultado.emailUsuario,
                    senha: resultado.senhaUsuario,
                    telefone: resultado.telefoneUsuario,
                    nome: resultado.nomeUsuario

                }

                res.status(201).send({
                    mensagem:"usuario criado",
                    usuairoCriado: usuario
                })  

            }
        )
    })


}) 

router.patch('/usuarios', (req, res, next) => {

    
    mysql.getConnection((error, conn) => {
        conn.query(
            'UPDATE usuario SET senhaUsuario = ? where emailUsuario = ?',
            [req.body.senhaUsuario, req.body.emailUsuario],
            (error, resultado, fields) => {
                conn.release()

                if(error){
                    return res.status(500).send({
                        error:error.message,
                        response:null
                    })
                }

                res.status(201).send({
                    mensagem:"usuario atualizado",
                    novaSenha: resultado.senhaUsuario
                })
            }
        )
    })

})

module.exports = router