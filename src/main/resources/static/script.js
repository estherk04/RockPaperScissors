const startBtn = document.getElementById("startBtn");
const gameArea = document.querySelector(".game-area");
const roundInfo = document.getElementById("roundInfo");
const playerMoveText = document.getElementById("playerMove");
const computerMoveText = document.getElementById("computerMove");
const resultText = document.getElementById("resultText");
const scoreText = document.getElementById("score");
const choices = document.querySelectorAll(".choice");

let playerName = "";
let totalRounds = 0;
let currentRound = 1;
let playerWins = 0;
let computerWins = 0;
let ties = 0;

startBtn.addEventListener("click", () => {
    playerName = document.getElementById("playerName").value || "Player";
    totalRounds = parseInt(document.getElementById("rounds").value);
    if (!totalRounds || totalRounds <= 0) return alert("Please enter a valid number of rounds.");

    gameArea.classList.remove("hidden");
    roundInfo.textContent = `Round ${currentRound} of ${totalRounds}`;
});

choices.forEach(choice => {
    choice.addEventListener("click", () => {
        const playerMove = choice.dataset.move;

        // Send move to backend
        fetch('/play', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ move: playerMove })
        })
            .then(res => res.json())
            .then(data => {
                const { playerName, playerMove, computerMove, result } = data;

                updateScore(result);

                playerMoveText.textContent = `${playerName} chose ${playerMove}`;
                computerMoveText.textContent = `Computer chose ${computerMove}`;
                resultText.textContent = `Result: ${result}`;
                scoreText.textContent = `${playerName}: ${playerWins} | Computer: ${computerWins} | Ties: ${ties}`;

                animateChoice(choice);

                currentRound++;
                if (currentRound <= totalRounds) {
                    roundInfo.textContent = `Round ${currentRound} of ${totalRounds}`;
                } else {
                    roundInfo.textContent = `Game Over!`;
                }
            })
            .catch(err => {
                console.error("Error playing move:", err);
            });
    });
});


function getComputerMove() {
    const moves = ["ROCK", "PAPER", "SCISSORS"];
    return moves[Math.floor(Math.random() * moves.length)];
}

function getResult(player, computer) {
    if (player === computer) return "TIE";
    if (
        (player === "ROCK" && computer === "SCISSORS") ||
        (player === "PAPER" && computer === "ROCK") ||
        (player === "SCISSORS" && computer === "PAPER")
    ) {
        return "WIN";
    }
    return "LOSE";
}

function updateScore(result) {
    if (result === "WIN") playerWins++;
    else if (result === "LOSE") computerWins++;
    else ties++;
}

function animateChoice(choiceElement) {
    choiceElement.classList.add("animate");
    setTimeout(() => choiceElement.classList.remove("animate"), 500);
}
document.getElementById("startBtn").addEventListener("click", () => {
    const name = document.getElementById("playerName").value || "Player";

    fetch("/start", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name })
    });

    gameArea.classList.remove("hidden");
    roundInfo.textContent = `Round ${currentRound} of ${totalRounds}`;
});

function playMove(move) {
    fetch("/play", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ move })
    })
        .then(res => res.json())
        .then(data => {
            const result = data.result;
            const playerMove = data.playerMove;
            const computerMove = data.computerMove;

            // update UI
            playerMoveText.textContent = `${playerName} chose ${playerMove}`;
            computerMoveText.textContent = `Computer chose ${computerMove}`;
            resultText.textContent = `Result: ${result}`;
        });
}
