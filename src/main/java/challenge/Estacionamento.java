package challenge;

import java.util.*;

public class Estacionamento {

    private static final int MAXIMO_CARRO_ESTACIONAMENTO = 10;

    private List<Carro> carrosEstacionados = new ArrayList<>();

    public void estacionar(Carro carro) {
        validarMotorista(carro);

        if(carrosEstacionados() >= MAXIMO_CARRO_ESTACIONAMENTO) {
            this.carrosEstacionados
                    .stream()
                    .filter(c -> c.getMotorista().getIdade() < Motorista.IDADE_SENIOR)
                    .findFirst()
                    .map(c -> this.carrosEstacionados.remove(c))
                    .orElseThrow(EstacionamentoException::new);
            this.carrosEstacionados.add(carro);
        } else {
            this.carrosEstacionados.add(carro);
        }
    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosEstacionados.contains(carro);
    }

    private void validarMotorista(Carro carro) {
        Motorista motorista = carro.getMotorista();
        if (Objects.isNull(motorista)) throw new EstacionamentoException();
        if (motorista.getIdade() < Motorista.IDADE_MINIMA_PARA_CARTEIRA || motorista.getPontos() > Motorista.MAXIMO_DE_PONTOS_NA_CARTEIRA) throw new EstacionamentoException();

    }
}
