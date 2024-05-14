package Osztalyok;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GameUI implements ActionListener {

    private JFrame frame = new JFrame("Kimaxoljuk");
    private JButton detachPipe = new JButton("Detach Pipe");
    private JButton throwPipe = new JButton("Throw Pipe");
    private JButton liftUpPipe = new JButton("Lift Up Pipe");
    private JButton liftUpPump = new JButton("Lift Up Pump");
    private JButton sticky = new JButton("Sticky");
    private JButton slippery = new JButton("Slippery");
    private JButton hole = new JButton("Hole");
    private JButton repair = new JButton("Repair");
    private JButton joinPipe = new JButton("Join Pipe");
    private JButton joinPump = new JButton("Join Pump");
    private JButton pass = new JButton("Pass");
    private JButton retool = new JButton("Retool");

    private JButton PumpatCistern = new JButton();
    private Field f;
    private int activePlayer=0;
    private int activeTeam=0;
    private boolean detach = false;

    private boolean joinpipe = false;
    private boolean detorjoin = false;

    private boolean bretool = false;

    private int turns = 0;

    public GameUI(Field f){
        this.f = f;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        detachPipe.addActionListener(this);
        detachPipe.setBounds(0, 720, 105, 30);

        throwPipe.addActionListener(this);
        throwPipe.setBounds(110, 720, 105, 30);

        liftUpPipe.addActionListener(this);
        liftUpPipe.setBounds(310, 720, 100, 30);

        liftUpPump.addActionListener(this);
        liftUpPump.setBounds(420, 720, 110, 30);

        sticky.addActionListener(this);
        sticky.setBounds(540, 720, 70, 30);

        slippery.addActionListener(this);
        slippery.setBounds(620, 720, 80, 30);

        hole.addActionListener(this);
        hole.setBounds(710, 720, 60, 30);

        repair.addActionListener(this);
        repair.setBounds(220, 720, 80, 30);

        joinPipe.addActionListener(this);
        joinPump.addActionListener(this);

        pass.addActionListener(this);
        pass.setBounds(670, 50, 100, 30);

        joinPump.addActionListener(this);
        joinPump.setBounds(670,100,100,30);

        retool.addActionListener(this);
        retool.setBounds(670,150,100,30);

        frame.add(hole);
        frame.add(liftUpPipe);
        frame.add(liftUpPump);
        frame.add(sticky);
        frame.add(slippery);
        frame.add(repair);
        frame.add(pass);
        frame.add(detachPipe);
        frame.add(throwPipe);
        frame.add(PumpatCistern);
        frame.add(joinPump);
        frame.add(retool);
        CreateMap(f);
    }
   public void NextPlayer() throws IOException {
        turns += 1;
        if(activePlayer == f.GetTeams(activeTeam).GetSize() - 1){
            NextTeam();
            activePlayer = 0;

            if(turns % (f.GetTeams(0).GetSize() + f.GetTeams(1).GetSize()) == 0){
                PumpatCistern.setVisible(true);
                f.Step(true, "");
            }

        }else if(activePlayer < f.GetTeams(activeTeam).GetSize()){
            activePlayer += 1;
        }
       f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(true);
    }

    void NextTeam(){
        if(activeTeam == 0){
            activeTeam = 1;
        }else if(activeTeam == 1){
            activeTeam = 0;
        }
    }

    void SetItem(String s) {

    }

    void CreateMap(Field f){
        PumpatCistern.getRootPane().setComponentZOrder(PumpatCistern, 0);
        PumpatCistern.setBounds(700, 610, 80, 80);
        ImageIcon Pumpicon = new ImageIcon("bin/Skins/Pump.png");
        PumpatCistern.setIcon(Pumpicon);

        for (int i = 0; i < f.GetPumps().size();i++){
            frame.add(f.GetPumps().get(i).GetPU().GetButton());
            f.GetPumps().get(i).GetPU().GetButton().setOpaque(false);
            f.GetPumps().get(i).GetPU().GetButton().addActionListener(this);
            f.GetPumps().get(i).GetPU().GetButton().getRootPane().setComponentZOrder(f.GetPumps().get(i).GetPU().GetButton(), 1);
        }
        for (int i = 0; i < f.GetPipes().size();i++){
            frame.add(f.GetPipes().get(i).GetPU().GetButton());
            f.GetPipes().get(i).GetPU().GetButton().setOpaque(false);
            f.GetPipes().get(i).GetPU().GetButton().addActionListener(this);
            f.GetPipes().get(i).GetPU().GetButton().getRootPane().setComponentZOrder(f.GetPipes().get(i).GetPU().GetButton(), 1);
        }

        frame.add(f.GetCistern().GetPU().GetButton());
        f.GetCistern().GetPU().SetPosAndHeight(0,600,800,0,100,0);
        f.GetCistern().GetPU().GetButton().setOpaque(false);
        f.GetCistern().GetPU().GetButton().addActionListener(this);
        f.GetCistern().GetPU().GetButton().getRootPane().setComponentZOrder(f.GetCistern().GetPU().GetButton(), 1);
            f.GetPumps().get(0).GetPU().SetPos(70,50);
            f.GetPumps().get(1).GetPU().SetPos(470,50);
            f.GetPumps().get(2).GetPU().SetPos(70,300);
            f.GetPumps().get(3).GetPU().SetPos(270,300);
            f.GetPumps().get(4).GetPU().SetPos(470,300);
            f.GetPumps().get(5).GetPU().SetPos(270,50);

        //Pipe1
        f.GetPipes().get(0).GetPU().SetPosAndHeight(f.GetPumps().get(0).GetPU().GetX(), f.GetPumps().get(0).GetPU().GetY()+80,
                f.GetPumps().get(2).GetPU().GetX(),f.GetPumps().get(0).GetPU().GetX(), f.GetPumps().get(2).GetPU().GetY(),f.GetPumps().get(0).GetPU().GetY());
        //Pipe2
        f.GetPipes().get(1).GetPU().SetPosAndHeight(f.GetPumps().get(2).GetPU().GetX()+80, f.GetPumps().get(3).GetPU().GetY(),
                f.GetPumps().get(3).GetPU().GetX(),f.GetPumps().get(2).GetPU().GetX(), f.GetPumps().get(2).GetPU().GetY(),f.GetPumps().get(3).GetPU().GetY());
        //Pipe3
        f.GetPipes().get(2).GetPU().SetPosAndHeight(f.GetPumps().get(5).GetPU().GetX()+80, f.GetPumps().get(1).GetPU().GetY(),
                f.GetPumps().get(1).GetPU().GetX(),f.GetPumps().get(5).GetPU().GetX(), f.GetPumps().get(1).GetPU().GetY(),f.GetPumps().get(5).GetPU().GetY());
       //Pipe4
        f.GetPipes().get(3).GetPU().SetPosAndHeight(f.GetPumps().get(3).GetPU().GetX()+80, f.GetPumps().get(3).GetPU().GetY(),
                f.GetPumps().get(4).GetPU().GetX(),f.GetPumps().get(3).GetPU().GetX(), f.GetPumps().get(4).GetPU().GetY(),f.GetPumps().get(3).GetPU().GetY());
       //Pipe5
        f.GetPipes().get(4).GetPU().SetPosAndHeight(f.GetPumps().get(1).GetPU().GetX(), f.GetPumps().get(1).GetPU().GetY()+80,
                f.GetPumps().get(4).GetPU().GetX(),f.GetPumps().get(1).GetPU().GetX(), f.GetPumps().get(4).GetPU().GetY(),f.GetPumps().get(1).GetPU().GetY());
       //Pipe6
        f.GetPipes().get(5).GetPU().SetPosAndHeight(f.GetPumps().get(0).GetPU().GetX()+80, f.GetPumps().get(0).GetPU().GetY(),
                f.GetPumps().get(5).GetPU().GetX(),f.GetPumps().get(0).GetPU().GetX(), f.GetPumps().get(5).GetPU().GetY(),f.GetPumps().get(0).GetPU().GetY());
        //Pipe7
        f.GetPipes().get(6).GetPU().SetPosAndHeight(f.GetPumps().get(5).GetPU().GetX(), f.GetPumps().get(5).GetPU().GetY()+80,
               f.GetPumps().get(3).GetPU().GetX(),f.GetPumps().get(5).GetPU().GetX(), f.GetPumps().get(3).GetPU().GetY(),f.GetPumps().get(5).GetPU().GetY());
        //Pipe8
        f.GetPipes().get(7).GetPU().SetPosAndHeight(f.GetPumps().get(3).GetPU().GetX(), f.GetPumps().get(3).GetPU().GetY()+80,
                f.GetPumps().get(3).GetPU().GetX()-190, f.GetCistern().GetPU().GetX(),f.GetCistern().GetPU().GetY() -380, f.GetPumps().get(3).GetPU().GetY());
        //Pipe9
        f.GetPipes().get(8).GetPU().SetPosAndHeight(f.GetPumps().get(4).GetPU().GetX(), f.GetPumps().get(3).GetPU().GetY()+80,
                f.GetPumps().get(4).GetPU().GetX()-390, f.GetCistern().GetPU().GetX(),f.GetCistern().GetPU().GetY() -380, f.GetPumps().get(4).GetPU().GetY());

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < f.GetTeams(i).GetSize(); j++){
                frame.add(f.GetTeams(i).GetPlayers(j).GetPU().GetButton());
                f.GetTeams(i).GetPlayers(j).GetPU().SetPos( f.GetTeams(i).GetPlayers(j).GetPipeline().GetPU().GetX(), f.GetTeams(i).GetPlayers(j).GetPipeline().GetPU().GetY());
                f.GetTeams(i).GetPlayers(j).GetPU().GetButton().getRootPane().setComponentZOrder(f.GetTeams(i).GetPlayers(j).GetPU().GetButton(), 0);
                f.GetTeams(i).GetPlayers(j).GetPU().GetButton().setEnabled(false);
            }
        }
        f.GetTeam(0).GetPlayer(0).GetPU().GetButton().setEnabled(true);
    }


    int height;
    int width;
    int x;
    int y;
    @Override
    public void actionPerformed(ActionEvent e) {

        Player p = f.GetTeam(activeTeam).GetPlayer(activePlayer);
        for (int i = 0; i < f.GetPumps().size();i++){
            if(e.getSource() == f.GetPumps().get(i).GetPU().GetButton()){
                p.PumpMove(f.GetPumps().get(i));
                if(p.GetLastmove()){
                    p.SetLastmove(false);
                    try{
                        f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                        if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                            if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                            f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                            f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                            detorjoin = false;
                        }
                        NextPlayer();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    p.GetPU().SetPos(p.GetPipeline().GetPU().GetX(), p.GetPipeline().GetPU().GetY());
                }

                else if(!detach  && joinpipe){
                    //p.JoinPipe();
                    p.GetItem().GetPU().SetPosAndHeight
                            (p.GetPipeline().GetPU().GetX(),p.GetPipeline().GetPU().GetY(),
                                    p.GetPipeline().GetPU().GetX(),f.GetPipes().get(i).GetPU().GetX(),
                                    p.GetPipeline().GetPU().GetY(),f.GetPipes().get(i).GetPU().GetY());
                    joinpipe = false;
                    detorjoin = false;
                    p.SetpickedupPipe(false);

                    try{
                        f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                        if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                            if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                            f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                            f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                            detorjoin = false;
                        }
                        NextPlayer();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    p.GetPU().SetPos(p.GetPipeline().GetPU().GetX(), p.GetPipeline().GetPU().GetY());
                }
            }
        }

        for (int i = 0; i < f.GetPipes().size();i++){
            if(e.getSource() == f.GetPipes().get(i).GetPU().GetButton()){
                if(detach){
                     height = f.GetPipes().get(i).GetPU().GetHeight();
                     width = f.GetPipes().get(i).GetPU().GetWidth();
                     x = f.GetPipes().get(i).GetPU().GetX();
                     y = f.GetPipes().get(i).GetPU().GetY();
                    p.DetachPipe(f.GetPipes().get(i));
                    if(f.GetPipes().get(i).GetPU().GetWidth() > f.GetPipes().get(i).GetPU().GetHeight()){
                        f.GetPipes().get(i).GetPU().resize(width/2, height);

                    }else if(f.GetPipes().get(i).GetPU().GetWidth() < f.GetPipes().get(i).GetPU().GetHeight()){
                        f.GetPipes().get(i).GetPU().resize(width, height/2);

                    }
                    if(p.GetPipeline().GetPU().GetY() > f.GetPipes().get(i).GetPU().GetY()){
                        f.GetPipes().get(i).GetPU().SetPos(p.GetPipeline().GetPU().GetX(), p.GetPipeline().GetPU().GetY() - height);
                    }
                    if(p.GetPipeline().GetPU().GetY() < f.GetPipes().get(i).GetPU().GetY()){
                        f.GetPipes().get(i).GetPU().SetPos(p.GetPipeline().GetPU().GetX(), p.GetPipeline().GetPU().GetY() + height);
                    }
                    if(p.GetPipeline().GetPU().GetX() < f.GetPipes().get(i).GetPU().GetX()){
                        f.GetPipes().get(i).GetPU().SetPos(p.GetPipeline().GetPU().GetX() + width , p.GetPipeline().GetPU().GetY());
                    }
                    if(p.GetPipeline().GetPU().GetX() > f.GetPipes().get(i).GetPU().GetX()){
                        f.GetPipes().get(i).GetPU().SetPos(p.GetPipeline().GetPU().GetX() - width, p.GetPipeline().GetPU().GetY());
                    }
                    detach = false;
                    detorjoin = true;
                    p.SetpickedupPipe(true);
                }else if(bretool){
                    p.Retool(f.GetPipes().get(i));
                    bretool = false;
                    try {
                        f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                        if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                            if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                            f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                            f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                            detorjoin = false;
                        }
                        NextPlayer();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    p.PipeMove(f.GetPipes().get(i));
                    p.GetPU().SetPos(p.GetPipeline().GetPU().GetX(), p.GetPipeline().GetPU().GetY());
                }

            }
        }

        if(e.getSource() == f.GetCistern().GetPU().GetButton()){
            p.CisternMove();
            p.GetPU().SetPos(p.GetPipeline().GetPU().GetX(), p.GetPipeline().GetPU().GetY());

        } else if (e.getSource() == detachPipe) {
            if(!detorjoin){
                detach = true;
            }
            else{
                joinpipe = true;
            }


        }else if (e.getSource() == throwPipe) {
            p.ThrowPipe();

        }else if (e.getSource() == liftUpPipe) {


        }else if (e.getSource() == liftUpPump) {
            if(p.GetPipeline() == f.GetCistern()){
                PumpatCistern.setVisible(false);
                int id = f.Getpumpmaxid()+1;

                Pump pu = new Pump(id,0, 0, 0, f.GetCistern(), f);
                f.SetNewPump(pu);
                p.LiftUpPump(pu);
                f.GetCistern().SetPumpSpawned(false);
                p.SetItem(pu);
                try {
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                    if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                        if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                        f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                        f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                        detorjoin = false;
                    }
                    NextPlayer();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            else{
                if( p.GetItem() != null){
                    PipeLine pu = p.GetItem();
                    pu.GetPU().SetPos(p.GetPU().GetX(),p.GetPU().GetY());
                    p.SetItem(null);
                }
            }
        }else if (e.getSource() == sticky) {
            p.Sticky();
            p.SetLastmove(true);

        }else if (e.getSource() == slippery) {
            p.Slip();
            try {
                f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                    if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                    detorjoin = false;
                }
                NextPlayer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if (e.getSource() == hole) {
            p.Hole();
            try {
                f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                    if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                    detorjoin = false;
                }
                NextPlayer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if (e.getSource() == repair) {
            p.Repair();
            try {
                f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                    if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                    detorjoin = false;
                }
                NextPlayer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if (e.getSource() == joinPipe) {
            p.JoinPipe();
            try {
                f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                    if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                    detorjoin = false;
                }
                NextPlayer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }else if (e.getSource() == joinPump) {

            if(p.GetItem() != null ){
                for (int i = 0; i < f.GetPipes().size();i++) {
                    if (p.GetPipeline() == f.GetPipes().get(i)) {
                        p.JoinPump((Pipe)p.GetPipeline(),(Pump)p.GetItem(),f);
                        PipeLineUI newpui = f.GetPipes().get(f.Getpipemaxid()-1).GetPU();
                        PipeLine newpl = f.GetPipes().get(f.Getpipemaxid()-1);
                        PipeLineUI onpipe = p.GetPipeline().GetPU();
                        PipeLineUI pumpui = p.GetItem().GetPU();

                        frame.add(newpui.GetButton());
                        frame.add(pumpui.GetButton());
                        pumpui.GetButton().setOpaque(false);
                        pumpui.GetButton().addActionListener(this);
                        pumpui.GetButton().getRootPane().setComponentZOrder(pumpui.GetButton(),1);

                        newpui.GetButton().setOpaque(false);
                        newpui.GetButton().addActionListener(this);
                        newpui.GetButton().getRootPane().setComponentZOrder(newpui.GetButton(), 1);

                        if(onpipe.GetWidth()>onpipe.GetHeight()){

                            newpui.SetPos(onpipe.GetX(),onpipe.GetY());
                            newpui.GetButton().setBounds(onpipe.GetX(),onpipe.GetY(),onpipe.GetWidth()/2,80);
                            pumpui.SetPos(onpipe.GetX()+onpipe.GetWidth()/2,onpipe.GetY());
                            pumpui.GetButton().setBounds(onpipe.GetX()+onpipe.GetWidth()/2,onpipe.GetY(),20,80);
                            onpipe.SetPos(onpipe.GetX()+onpipe.GetWidth()/2 + 20  ,onpipe.GetY());

                            onpipe.GetButton().setBounds(onpipe.GetX(),onpipe.GetY(),onpipe.GetWidth()-onpipe.GetWidth()/2 -20,80);


                        } else if(onpipe.GetWidth()<onpipe.GetHeight()&& !((Pipe) p.GetPipeline()).GetIsOnCistern()){

                            newpui.SetPos(onpipe.GetX(),onpipe.GetY());
                            newpui.GetButton().setBounds(onpipe.GetX(),onpipe.GetY(),80,onpipe.GetHeight()/2);
                            pumpui.SetPos(onpipe.GetX(),onpipe.GetY()+onpipe.GetHeight()/2);
                            pumpui.GetButton().setBounds(onpipe.GetX(),onpipe.GetY()+onpipe.GetHeight()/2,80,20);
                            onpipe.SetPos(onpipe.GetX()  ,onpipe.GetY()+onpipe.GetHeight()/2 + 20);

                            onpipe.GetButton().setBounds(onpipe.GetX(),onpipe.GetY(),80,onpipe.GetHeight()-onpipe.GetHeight()/2 -20);


                        }else{
                            newpui.SetPos(onpipe.GetX(),onpipe.GetY());
                            newpui.GetButton().setBounds(onpipe.GetX(),onpipe.GetY(),80,onpipe.GetHeight()/2);
                            pumpui.SetPos(onpipe.GetX(),onpipe.GetY()+onpipe.GetHeight()/2);
                            pumpui.GetButton().setBounds(onpipe.GetX(),onpipe.GetY()+onpipe.GetHeight()/2,80,20);
                            onpipe.SetPos(onpipe.GetX()  ,onpipe.GetY()+onpipe.GetHeight()/2 + 20);

                            onpipe.GetButton().setBounds(onpipe.GetX(),onpipe.GetY(),80,onpipe.GetHeight()-onpipe.GetHeight()/2 -20);


                        }


                    }
                }
                p.PumpMove(p.GetItem());
                p.SetItem(null);
            }
        }
        else if(e.getSource() == pass){
            try {
                f.GetTeam(activeTeam).GetPlayer(activePlayer).GetPU().GetButton().setEnabled(false);
                if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetpickedupPipe()){
                    if(f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem() != null) f.GetTeam(activeTeam).GetPlayer(activePlayer).GetItem().GetPU().SetPosAndHeight(x,y,width,0,height,0);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetpickedupPipe(false);
                    f.GetTeam(activeTeam).GetPlayer(activePlayer).SetItem(null);
                    detorjoin = false;
                }
                NextPlayer();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }else if (e.getSource() == retool){
            bretool = true;

        }
    }

}