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
        'where h.idHorario = c.idHorario and u.emailUsuario = ?'
        
        conn.query(
            query,
            [req.usuario.emailUsuario],
            (error, results) => {
                conn.release()
                if(error) { console.log(error); return res.status(500).send({error: error})}                
                return res.status(200).send(results)
            }
        )            
    })
})

router.get('/:data', (req, res) => {
    mysql.getConnection((error, conn) => {

        if(error) { console.log(error); return res.status(500).send({ error: error})}
        const query = 'select e.nomeEstabelecimento, h.horario from horario h, estabelecimento e where DATE(h.horario) = ? and disponivel = 1'

        conn.query(
            query,
            [req.params.data],
            (error, results) => {
                conn.release()
                if(error) { console.log(error); return res.status(500).send({ error: error})}                
                return res.status(200).send(results)
            }
        )
    })
})

router.post('/agendar', login, (req,res,next) => {
    mysql.getConnection((error, conn) => {
        if(error) { return res.status(500).send({ error: error }) }
        const query = 'insert into consulta(emailUsuario, idHorario) values ('+
                        '?,'+
                        '(select h.idHorario from horario h, estabelecimento e where h.horario = ? and e.nomeEstabelecimento = ?))'
        conn.query(
            query,
            [req.usuario.emailUsuario, req.body.horario, req.body.nomeEstabelecimento],
            (error, results) => {
                conn.release()
                if(error) { console.log(error); return res.status(500).send({ error: error })}
                return res.status(201).send({ mensagem: "horario agendado"})
                
            }
        )
        
    })
})
module.exports =  router    