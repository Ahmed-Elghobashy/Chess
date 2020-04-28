package Game;

import java.util.ArrayList;

import exceptions.NotValidMoveException;
import exceptions.NotYourTurnException;
import model.pieces.Bishop;
import model.pieces.King;
import model.pieces.Knight;
import model.pieces.Pawn;
import model.pieces.Piece;
import model.pieces.PieceColour;
import model.pieces.Queen;
import model.pieces.Rook;

public class Board implements GameValidator,PlayerListener
{
	private Piece[][] board;
	private Player curretPlayer;
	private Player opponent;
	private GameListener listener;
	
	
	 public Board(Player player1 , Player player2)
	{
		if(player1.getColour()==PieceColour.WHITE)
		{
			curretPlayer=player1;
			opponent=player2;
		}
		else 
		{
			opponent=player1;
			curretPlayer=player2;
		}
		curretPlayer.setValidator(this);
		curretPlayer.setListener(this);
		opponent.setListener(this);
		opponent.setValidator(this);
		board = new Piece[8][8];
		for(int i =0;i<8;i++)
		{
			board[1][i]= new Pawn(1,i,player1.getColour());
		}
		board[0] [0] = new Rook(0, 0, player1.getColour());
		board[0] [1] = new Knight(0, 1, player1.getColour());
		board[0] [2] = new Bishop(0, 2, player1.getColour());
		board[0] [3] = new Queen(0, 3, player1.getColour());
		board[0] [4] = new King(0, 4, player1.getColour());
		board[0] [5] = new Bishop(0, 5, player1.getColour());
		board[0] [6] = new Knight(0, 6, player1.getColour());
		board[0] [7] = new Rook(0,7,player1.getColour());
		player1.setKing((King)board[0][4]);
		for(int i =0;i<8;i++)
		{
			board[6][i]= new Pawn(6,i,player2.getColour());
		}
		board[7] [0] = new Rook(7, 0, player2.getColour());
		board[7] [1] = new Knight(7, 1, player2.getColour());
		board[7] [2] = new Bishop(7, 2, player2.getColour());
		board[7] [3] = new Queen(7, 3, player2.getColour());
		board[7] [4] = new King(7, 4, player2.getColour());
		board[7] [5] = new Bishop(7, 5, player2.getColour());
		board[7] [6] = new Knight(7, 6, player2.getColour());
		board[7] [7] = new Rook(7,7,player2.getColour());
		player2.setKing((King)board[7][4]);
		for(int i = 0 ; i<board.length;i++)
		{
			for(int j =0 ;j<board[i].length;j++)
			{
				if(board[i][j]!=null)
				{	
				if(board[i][j].getColour()==player1.getColour())
					player1.getPieces().add(board[i][j]);
				else 
				{
					player2.getPieces().add(board[i][j]);
				
						
				}
					
				}
					
			}
		}
		
		
		
	}
	
	
	@Override
	public void validateFriendlyPiece(Piece piece, int destX, int destY) throws NotValidMoveException
	{
		Piece attackedPiece = board[destX][destY];
		if(attackedPiece==null)
			return;
		if(attackedPiece.getColour()==piece.getColour())
			throw new NotValidMoveException();
		
	}
	@Override
	public void validateCheckMate(Piece piece, int destX, int destY) throws NotValidMoveException, CloneNotSupportedException
	{
		if(piece instanceof King)
		{
			for(Piece p : opponent.getPieces())
			{
				if(p.isValidPieceMove(destX, destY))
					if(canGetThere(p, destX, destY))
						throw new NotValidMoveException();
			}
		}
		else 
		{
		Piece[][] tempBoard = cloneBoard();
		tempBoard[piece.getPositionX()][piece.getPositionY()] =null;
		tempBoard[destX][destY] = piece.clone();
		
		if(isKingThreatened(tempBoard, curretPlayer.getColour()))
		{
			throw new NotValidMoveException();
		}
		}
		
	}
	@Override
	public void validatePiecebyPass(Piece piece, int destX, int destY) throws NotValidMoveException
	{
	
		if(!canGetThere(piece, destX, destY))
		{
			throw new NotValidMoveException();
		}
		
		
		
	}
	@Override
	public void validatePieceMove(Piece piece, int destX, int destY) throws NotValidMoveException
	{
		if(!piece.isValidPieceMove(destX, destY))
			throw new NotValidMoveException();
		
	}
	
	
	public void onEndTurn() throws CloneNotSupportedException 
	{
		 Player temPlayer = curretPlayer;
		 curretPlayer=opponent;
		 opponent=temPlayer;
		 if(isCheckMate())
		 {
			 System.out.println("I got here");
			 listener.onGameOver();
		 }
		 
		 
		
	}

	
	public void validateTurn(Player player) throws NotYourTurnException
	{
		if(player!=curretPlayer)
			throw new NotYourTurnException();
		
	}
	
	public boolean canGetThere(Piece piece, int destX, int destY)
	{
		if(piece instanceof King || piece instanceof Knight)
			return true;
		
		int diffrenceX =Math.abs(piece.getPositionX()-destX);
		int realDiffrenceX = destX-piece.getPositionX();
		int diffrenceY =Math.abs(piece.getPositionY()-destY);
		int realDiffrenceY = destY-piece.getPositionY();
		if(piece instanceof Pawn)
		{
			if(diffrenceX==1 && diffrenceY==0)
			{
				if(board[piece.getPositionX()+realDiffrenceX][piece.getPositionY()]==null)
					return true;
				else 
					return false;
			}
			if(diffrenceX==diffrenceY && diffrenceX==1)
			{
				if(board[piece.getPositionX()+realDiffrenceX][piece.getPositionY()+realDiffrenceY] != null)
					return true;
				else 
					return false;
			}
			if(diffrenceX==2)
			{
				if(realDiffrenceX==-2)
				{
					if(board[piece.getPositionX()-1][piece.getPositionY()]==null)
						return true;
				}
				else
				{
					if(realDiffrenceX==2)
						if(board[piece.getPositionX()+1][piece.getPositionY()]==null)
							return true;
				}
			}
			else 
				return false;
		}
		if(diffrenceX+diffrenceY==1)
			return true;
		if(piece.isLateralMove(destX, destY))
		{
			if(realDiffrenceX>0 && realDiffrenceY>0)
			{	
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()+i][piece.getPositionY()+i]!=null)
					{
						System.out.println("I throw here 1");
						return false;
						
					}	
				}
			}
			
			if(realDiffrenceX<0 && realDiffrenceY>0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()-i][piece.getPositionY()+i]!=null)
						{
						System.out.println("I throw here 1");
						return false;
						}
				}

			}
			
			if(realDiffrenceX>0 && realDiffrenceY<0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()+i][piece.getPositionY()-i]!=null)
						return false;
				}

			}
			
			if(realDiffrenceX<0 && realDiffrenceY<0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()-i][piece.getPositionY()-i]!=null)
						return false;
				}

			}
		}
		
		
		if(piece.isVerticalMovement(destX,destY))
		{
			if(realDiffrenceX>0)
			{	
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()+1][piece.getPositionY()]!=null)
						return false;
				}
			}
			if(realDiffrenceX<0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()-1][piece.getPositionY()]!=null)
						return false;
				}
			}
			
		}
		
		if(piece.isHorizontalMovement(destX, destY))
		{
			if(realDiffrenceY>0)
			{	
				for(int i =1;i<diffrenceY;i++)
				{
					if(board[piece.getPositionX()][piece.getPositionY()+i]!=null)
						return false;
				}	
			}
			if(realDiffrenceY<0)
			{
				for(int i =1;i<diffrenceY;i++)
				{
					if(board[piece.getPositionX()][piece.getPositionY()-i]!=null)
						return false;
				}	
			}
		}
		return true;
	}
	
	public boolean canGetThere(Piece piece, int destX, int destY,Piece[][] board)
	{
		if(piece instanceof King || piece instanceof Knight || piece instanceof Pawn)
			return true;
		int diffrenceX =Math.abs(piece.getPositionX()-destX);
		int realDiffrenceX = destX-piece.getPositionX();
		int diffrenceY =Math.abs(piece.getPositionY()-destY);
		int realDiffrenceY = destY-piece.getPositionY();
		
		if(diffrenceX+diffrenceY==1)
			return true;
		if(piece.isLateralMove(destX, destY))
		{
			if(realDiffrenceX>0 && realDiffrenceY>0)
			{	
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()+i][piece.getPositionY()+i]!=null)
					{
						System.out.println("I throw here");
						return false;
						
					}	
				}
			}
			
			if(realDiffrenceX<0 && realDiffrenceY>0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()-i][piece.getPositionY()+i]!=null)
						{
						System.out.println("I throw here");
						return false;
						}
				}

			}
			
			if(realDiffrenceX>0 && realDiffrenceY<0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()+i][piece.getPositionY()-i]!=null)
						return false;
				}

			}
			
			if(realDiffrenceX<0 && realDiffrenceY<0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()-i][piece.getPositionY()-i]!=null)
						return false;
				}

			}
		}
		
		
		if(piece.isVerticalMovement(destX,destY))
		{
			if(realDiffrenceX>0)
			{	
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()+1][piece.getPositionY()]!=null)
						return false;
				}
			}
			if(realDiffrenceX<0)
			{
				for(int i =1;i<diffrenceX;i++)
				{
					if(board[piece.getPositionX()-i][piece.getPositionY()]!=null)
						return false;
				}
			}
			
		}
		
		if(piece.isHorizontalMovement(destX, destY))
		{
			if(realDiffrenceY>0)
			{	
				for(int i =1;i<diffrenceY;i++)
				{
					if(board[piece.getPositionX()][piece.getPositionY()+i]!=null)
						return false;
				}	
			}
			if(realDiffrenceY<0)
			{
				for(int i =1;i<diffrenceY;i++)
				{
					if(board[piece.getPositionX()][piece.getPositionY()-i]!=null)
						return false;
				}	
			}
		}
		return true;
	}
	
	
	
	public boolean isCheckMate() throws CloneNotSupportedException
	{
		if(!isKingThreatened(board, curretPlayer.getColour()))
			{
			System.out.println("I got frrrrrrrom here");
			return false;
			}
		for(Piece piece : curretPlayer.getPieces())
		{
			for(int i=0;i<board.length;i++)
			{
				for(int j=0 ; j<board[i].length;j++)
				{
					
					if(piece.isValidPieceMove(i,j) && (board[i][j]==null || board[i][j].getColour()==opponent.getColour()))
					{
						if(canGetThere(piece, i, j))
						{
						  if(piece instanceof King)
						  {
							  try
							{
								validateCheckMate(piece, i, j);
								return true;
							} catch (NotValidMoveException | CloneNotSupportedException e)
							{
								continue;
							}
						  }
						 Piece[][] tempBoard = cloneBoard();
						 tempBoard[piece.getPositionX()][piece.getPositionY()]=null;
						 tempBoard[i][j]=piece.clone();
						 if(!isKingThreatened(tempBoard,curretPlayer.getColour()))
						 {
							 System.out.println("I got out from here");
							 printBoard(tempBoard);
							 return false;
						 }
						}
					}
					
				}
			}
		}
		System.out.println("It is check");
		return true;
	}
	
	
	public boolean  isKingThreatened(Piece[][] board ,PieceColour colour)
	{
		ArrayList<Piece> opponentPieces = new ArrayList<Piece>();
		ArrayList<Piece> currentPieces = new ArrayList<Piece>();
		King king=null ;
		for(int i = 0 ; i<board.length;i++)
		{
			for(int j =0 ;j<board[i].length;j++)
			{
				if(board[i][j]!=null)
				{	
				if(board[i][j].getColour()!=colour)
					opponentPieces.add(board[i][j]);
				else 
				{
					currentPieces.add(board[i][j]);
					if(board[i][j] instanceof King)
						king = (King) board[i][j];
						
				}
					
				}
					
			}
		}
		int kingLocationX = king.getPositionX();
		int kingLoctionY = king.getPositionY();
		for(Piece piece :opponentPieces)
		{
			if(piece.isValidPieceMove(kingLocationX, kingLoctionY))
				if(canGetThere(piece, kingLocationX, kingLoctionY,board))
					return true;
		}
		
		return false;

	}
	
	@Override
	public void movePiece(Piece piece, int destX, int destY)
	{
		if(piece instanceof Pawn)
			((Pawn) piece).setNotPlayed(false);
		board[piece.getPositionX()][piece.getPositionY()]=null;
		Piece removedPiece =board[destX][destY];
		opponent.getPieces().remove(removedPiece);
		board[destX][destY]=piece;
		
	}
	
	public Piece[][] cloneBoard() throws CloneNotSupportedException
	{
		Piece[][] retArray= new Piece[8][8];
		for(int i =0;i<=7;i++)
		{
			for(int j =0 ;j<=7;j++)
			{
				if(board[i][j]!=null)
					retArray[i][j]=board[i][j].clone();
				else 
					retArray[i][j]=null;
			}
		}
		return retArray;
	}


	public Piece[][] getBoard()
	{
		return board;
	}


	public void setBoard(Piece[][] board)
	{
		this.board = board;
	}


	public Player getCurretPlayer()
	{
		return curretPlayer;
	}


	public void setCurretPlayer(Player curretPlayer)
	{
		this.curretPlayer = curretPlayer;
	}


	public Player getOpponent()
	{
		return opponent;
	}


	public void setOpponent(Player opponent)
	{
		this.opponent = opponent;
	}


	public GameListener getListener()
	{
		return listener;
	}


	public void setListener(GameListener listener)
	{
		this.listener = listener;
	}
	
	public static void main(String[] args) throws NotValidMoveException, NotYourTurnException, CloneNotSupportedException
	{
		Player player1 = new Player(PieceColour.WHITE);
		Player player2 = new Player(PieceColour.BLACk);
		Board model = new Board(player1, player2);
		player1.playMove(model.getBoard()[1][4],2,4);
		player2.playMove(model.getBoard()[6][7],5,7);
		player1.playMove(model.getBoard()[0][6],2,7);
		player2.playMove(model.getBoard()[5][7],4,7);
		player1.playMove(model.getBoard()[0][3],2,5);
		player2.playMove(model.getBoard()[4][7],3,7);
		player1.playMove(model.getBoard()[2][7],4,6);
		player2.playMove(model.getBoard()[7][7],6,7);
		printBoard(model.getBoard());
		player1.playMove(model.getBoard()[2][5],6,5);
		printBoard(model.getBoard());
	}
	
	public static void printBoard(Piece[][] board)
	{
		for(int i =0; i<8;i++)
		{
			for(int j=0; j<8;j++)
			{
				if(board[i][j]!=null)
					System.out.print(board[i][j].getName()+",");
				else 
					System.out.print(board[i][j]+",");
			}
			System.out.println("");
		}
		System.out.println("");
	}


	@Override
	public void validatePawnMove(Pawn pawn, int destX, int destY)
	{
		int diffrenceX =destX- pawn.getPositionX();

		
	}
	
	
}
