package br.edu.ipog.screenmusic.principal;

import br.edu.ipog.screenmusic.model.Album;
import br.edu.ipog.screenmusic.model.Artista;
import br.edu.ipog.screenmusic.model.Musica;
import br.edu.ipog.screenmusic.service.ScreenSoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MusicaApp implements CommandLineRunner {

    @Autowired
    private ScreenSoundService screenSoundService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            mostrarMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            processarOpcao(option, scanner);
        } while (option != 0);
    }

    private void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Cadastrar Artista");
        System.out.println("2. Cadastrar Álbum");
        System.out.println("3. Adicionar Música");
        System.out.println("4. Listar Artistas");
        System.out.println("5. Listar Álbuns de um Artista");
        System.out.println("6. Listar Músicas");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: \n");
    }

    private void processarOpcao(int option, Scanner scanner) {
        switch (option) {
            case 1 -> cadastrarArtista(scanner);
            case 2 -> cadastrarAlbum(scanner);
            case 3 -> adicionarMusica(scanner);
            case 4 -> listarArtistas();
            case 5 -> listarAlbunsDoArtista(scanner);
            case 6 -> listarMusicas();
            case 0 -> System.out.println("Saindo...");
            default -> System.out.println("Opção inválida.");
        }
    }

    private void cadastrarArtista(Scanner scanner) {
        System.out.print("Digite o nome do artista: ");
        var nome = scanner.nextLine();
        if (nome.isEmpty()) {
            System.out.println("O nome do artista não pode estar vazio.");
            return;
        }
        try {
            Artista artista = new Artista();
            artista.setNome(nome);
            screenSoundService.cadastrarArtista(artista);
            System.out.println("Artista cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar artista: " + e.getMessage());
        }
    }

    private void cadastrarAlbum(Scanner scanner) {
        System.out.print("Digite o nome do álbum: ");
        var nomeAlbum = scanner.nextLine();
        System.out.print("Digite o nome do artista: ");
        var nomeArtista = scanner.nextLine();

        try {
            Artista artista = buscarArtista(nomeArtista);
            if (artista != null) {
                Album album = new Album();
                album.setNome(nomeAlbum);
                album.setArtista(artista);
                screenSoundService.adicionarAlbum(album);
                System.out.println("Álbum cadastrado com sucesso!");
            } else {
                System.out.println("Artista não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar álbum: " + e.getMessage());
        }
    }

    private void adicionarMusica(Scanner scanner) {
        System.out.print("Digite o título da música: ");
        var titulo = scanner.nextLine();
        System.out.print("Digite o nome do artista: ");
        var nomeArtista = scanner.nextLine();
        System.out.print("Digite o nome do álbum: ");
        var nomeAlbum = scanner.nextLine();

        try {
            Artista artista = buscarArtista(nomeArtista);
            if (artista != null) {
                Album album = buscarAlbum(nomeAlbum, artista);
                if (album != null) {
                    screenSoundService.adicionarMusica(titulo, artista, album);
                    System.out.println("Música adicionada com sucesso!");
                } else {
                    System.out.println("Álbum não encontrado.");
                }
            } else {
                System.out.println("Artista não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao adicionar música: " + e.getMessage());
        }
    }

    private void listarArtistas() {
        try {
            List<Artista> artistas = screenSoundService.listarArtistas();
            System.out.println("Artistas cadastrados:");
            for (Artista artista : artistas) {
                System.out.println(artista.getNome());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar artistas: " + e.getMessage());
        }
    }

    private void listarAlbunsDoArtista(Scanner scanner) {
        System.out.print("Digite o nome do artista: ");
        var nomeArtista = scanner.nextLine();

        try {
            Artista artista = buscarArtista(nomeArtista);
            if (artista != null) {
                List<Album> albuns = screenSoundService.listarAlbunsPorArtista(artista);
                System.out.println("Álbuns do artista " + artista.getNome() + ":");
                for (Album album : albuns) {
                    System.out.println(album.getNome());
                }
            } else {
                System.out.println("Artista não encontrado.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar álbuns: " + e.getMessage());
        }
    }

    private void listarMusicas() {
        try {
            List<Musica> musicas = screenSoundService.listarMusicas();
            System.out.println("Músicas cadastradas:");
            for (Musica musica : musicas) {
                System.out.println(musica.getTitulo());
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar músicas: " + e.getMessage());
        }
    }

    private Artista buscarArtista(String nomeArtista) {
        List<Artista> artistas = screenSoundService.listarArtistas();
        return artistas.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nomeArtista))
                .findFirst()
                .orElse(null);
    }

    private Album buscarAlbum(String nomeAlbum, Artista artista) {
        List<Album> albuns = screenSoundService.listarAlbunsPorArtista(artista);
        return albuns.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nomeAlbum))
                .findFirst()
                .orElse(null);
    }
}
