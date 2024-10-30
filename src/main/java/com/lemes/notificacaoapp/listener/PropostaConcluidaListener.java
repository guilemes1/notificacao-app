package com.lemes.notificacaoapp.listener;

import com.lemes.notificacaoapp.constante.MensagemConstante;
import com.lemes.notificacaoapp.domain.Proposta;
import com.lemes.notificacaoapp.service.NotificacaoSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropostaConcluidaListener {

    @Autowired
    private NotificacaoSnsService notificacaoSnsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
    public void propostaPendente(Proposta proposta) {
        String mensagem = null;

        if (proposta.getAprovada()) {
            mensagem = String.format(MensagemConstante.PROPOSTA_APROVADA, proposta.getUsuario().getNome());
        } else {
            mensagem = String.format(MensagemConstante.PROPOSTA_REPROVADA, proposta.getUsuario().getNome(), proposta.getObservacao());
        }
        notificacaoSnsService.notificar(proposta.getUsuario().getTelefone(), mensagem);
    }
}
