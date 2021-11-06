const express = require('express')
const app = express()   


const rotaAgendamentos = require('./routes/agendamentos')
const rotaUsuarios = require('./routes/usuarios')

app.use(express.urlencoded({extended: true}))
app.use(express.json())

app.use((req,res,next)=> {
    res.header('Access-Control-Allow-Origin', '*')
    res.header('Access-Control-Allow-Header',
        'Origin, X-Requested-With, Content-Type, Accept, Authorization'
    )

    if(req.method === 'OPTIONS'){
        res.header('Access-Control-Allow-Methods', 'GET,POST,DELETE,PUT,PATCH')
        return res.status(200).send({})
    }

    next()
})

app.use('/api/esag/agendamentos', rotaAgendamentos )
app.use('/api/esag/usuarios', rotaUsuarios) 

app.use((req,res,next) => {
    const erro = new Error('Não encontrado')
    erro.status = 404
    next(erro)
    
    
})

module.exports = app