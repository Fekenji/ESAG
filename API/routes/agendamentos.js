const express = require('express')
const router = express.Router()
const login = require('../middleware/login')
const mysql = require('../mysql').pool
const jwt = require('jsonwebtoken')

router.get('/', login, (req,res,next) => {
    mysql.getConnection((error, conn) => {
        
        if(error) { console.log(error); return res.status(500).send({error: error}) }
        const query = 'select e.nomeEstabelecimento, h.horario, e.localizacao from ' +
        'consulta c,' +
        'usuario u,' +
        'estabelecimento e,' +
        'horario h ' +
        'where c.emailUsuario = u.emailUsuario ' +
        'and u.emailUsuario = ?'
        
        conn.query(
            query,
            [req.usuario.emailUsuario],
            (error, results) => {
                conn.release()
                if(error) { console.log(error); return res.status(500).send({error: error})}
                console.log({resultado: results})
                return res.status(200).send(results)
            }
        )            
    })
})

router.get('/:data', (req, res) => {
    mysql.getConnection((error, conn) => {

        if(error) { console.log(error); return res.status(500).send({ error: error})}
        const query = 'select e.nomeEstabelecimento, h.horario from horario h, estabelecimento e where DATE(h.horario) = ?'

        conn.query(
            query,
            [req.params.data],
            (error, results) => {
                conn.release()
                if(error) { console.log(error); return res.status(500).send({ error: error})}
                console.log(results)
                return res.status(200).send(results)
            }
        )
    })
})

router.post('/', (req,res,next) => {
    const agendamento = {
        
    }
})
module.exports =  router    